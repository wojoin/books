- [C++ Templates](#c-templates)
- [Part I: The Basic](#part-i-the-basic)
  - [Chapter 1 Function Template](#chapter-1-function-template)
    - [1.1 函数模板例子](#11-函数模板例子)
      - [定义函数模板](#定义函数模板)
      - [使用函数模板](#使用函数模板)
    - [1.2 模板参数推导](#12-模板参数推导)
    - [1.3 多个模板参数](#13-多个模板参数)
      - [返回值类型的模板参数](#返回值类型的模板参数)
      - [推导返回类型](#推导返回类型)
      - [返回值作为公共类型](#返回值作为公共类型)
    - [1.4 默认模板实参default template arguments](#14-默认模板实参default-template-arguments)
    - [1.5 重载函数模板](#15-重载函数模板)

# C++ Templates

# Part I: The Basic

这部分介绍C++模板的常用概念和**语言特性**。
通过函数模板和类模板的例子来讨论范型的目标和概念。
一些基础的模板特性
- typename 关键字
- 模板参数
- 可变模板
- 成员模板
- 移动语义
- 编译时如何使用范性代码

为什么要使用模板？
C++要求我们使用类型声明变量、函数以及其他类型的实体。然而需要代码看起来基本一样，除了类型不同。
如快速排序算法，有时应用在int的array上，有时应用在string的vector上，在快排内部实现上，只要求数据元素相互可以比较即可。
如果语言本身不支持范型特性的话，你可以会有一些糟糕的选择：
1. 需要为不同数据类型重复实现同一种算法
2. 为常见的基本类型 `void*`, `Object`写范型代码

这里每一种方法都有其缺点
1. 对于第一种，你在重新发明轮子。
2. 对于第二种，为公共基类写范型代码，那么会失去类型检查的好处

模板解决了这个问题，没有这些缺点。模板是一些还未指定类型的函数或类。当你使用模板时，需要传递类型给模板。
由于模板是一种语言特性，所以它完全支持类型检查。
STL标准库中，几乎都是模板。如容器类container classes.

模板还允许我们参数化行为parameterize behavior、优化代码和参数化信息parameterize information。
这些我们将在后面讨论.

## Chapter 1 Function Template

### 1.1 函数模板例子

#### 定义函数模板

函数模板是参数化的函数，所以它们表示一族函数。函数模板看起来和普通函数很像，除了一些元素是未决定的，这些未决定的元素是可以参数化的。

```C++
template<typename T>
T max(T a, T b)
{
    return b < a ? a : b;
}
```
a, b作为函数参数，它们的类型是`template parameter模板参数T`.
模板参数必须下面的语法进行声明: `template< comma-separated-list-of-parameters >`
本例中模板参数是`typename T`, 关键字 `typename` 表示引入一个`类型参数type parameter`,这是迄今为止模板编程中最常用的一种类型了。
也有其他一些类型: 非类型模板参数nontype template parameter. 由于历史原因，也可以使用`class`关键字定义类型参数。

#### 使用函数模板
```C++
int main() 
{
    int i = 42;
    std::cout << "max(7,i):" << ::max(7,i) << ’\n’;
    
    double f1 = 3.4;
    double f2 = -6.7;
    std::cout << "max(f1,f2): " << ::max(f1,f2) << ’\n’;
    
    std::string s1 = "mathematics";
    std::string s2 = "math";
    std::cout << "max(s1,s2): " << ::max(s1,s2) << ’\n’;
}
```
这里生成了三次模板max，模板不是编译进单个实体处理任意的类型，而是在模板使用时，根据使用参数的不同类型生成不同的实体。
因此max函数编译为三种类型的函数，int, float, string.
例如对于 int,  有如下函数
```C++
int max (int a, int b)
{
    return b<a?a:b; 
}
```
这种通过具体参数替换模板参数的过程叫做**实例化instantiation**, 产生了一个模板的**实例instance**.


```C++
std::complex<float> c1, c2; // doesn’t provide operator < 
...
::max(c1,c2); // ERROR at compile time
```

模板的编译发生在两个阶段:
1. 无实例的定义时，模板代码本身正确性的检查，会忽略模板参数，包括
   1. 语法
   2. 使用不依赖模板参数的名字，如max中的 `<`
   3. 不依赖模板参数的静态断言
2. 实例化时，此时模板代码会再检查一次，此时,所有依赖于模板参数的部分会检查

名字被检查两次叫做`两阶段查找two-phase lookup`

### 1.2 模板参数推导
当我们调用函数模板时，模板参数时通过传递的参数决定的，也就是当我们传递函数参数int给max模板时，这时编译器能够推导出模板参数T的类型时int.
但是，模板参数T可能是实参类型的部分，如
```C++
template<typename T>
T max (T const& a, T const& b)
{
    return b < a ? a : b;
}
```
但是传递的参数类型是`int`, 但是函数参数的类型是`int const&`

**类型推断期间的类型转换**
类型转换会自动发生在类型推断时期
1. 参数类型是引用传递时，不会发生类型转换。
2. 参数类型是值传递时，会发生decay类型转换: const, violate修饰符会被忽略，引用转为引用，原始数组或指针转换为对应的指针类型。
例如
```C++
template<typename T>
T max(T a, T b);

int const c = 42;
max(c, c);  // T 推导为int

int i = 20;
int& ir = i;
max(i, ir); // T 推导为int
```
但是下面这中调用会发生错误
```C++
max(4, 4.2);    // ERROR: T can be deduced as int or double
```
解决方法有两种

1. 将参数转换成都匹配函数参数的类型, `max(static_cast<double>(4), 4.2);`
2. 显式地指定参数T的类型，阻止编译器的类型推断， `max<double>(4, 4.2);`

**默认参数的类型推导**
类型推导不适用于默认调用参数。
```C++
template<typename T>
void f(T = "");

f(1);   // OK: deduced T to be int, so that it calls f<int>(1)
f();    // ERROR: cannot deduce T
```
为了支持这样的情况，可以使用`模板参数的默认参数`
```C++
template<typename T = std::string>
void f(T = "");

f();    // ok
```

### 1.3 多个模板参数
函数模板有两种不同类型的参数
1. 模板参数template parameter, 声明在尖括号内，函数模板名字之前, max中的 typename T 中的T是模板参数。
2. 调用参数call argument，参数a,b时调用参数。

你也可以声明像下面这样
```C++
// OK, but type of first argument defines return type
template<typename T1, typename T2>
T1 max(T1 a, T2 b)
{
    return b < a ? a : b;
}

...
auto m1 = ::max(42, 66.66); // m1 = 66, 这种方式的调用都没有指定模板参数
auto m2 = ::max(66.66, 42); // m2 = 66.66
```
由于返回值声明是T1, 所以饭回的不同依赖于调用参数的顺序。
C++是如何处理这个问题的？
- 引入第三个模板参数，返回值参数
- 编译器决定返回值
- 声明返回值为这两种参数类型的公共类型

#### 返回值类型的模板参数
```C++
template<typename T1, typename T2, typename RT>
RT max (T1 a, T2 b);
...
::max<int,double,double>(4, 7.2);  // OK, 但是很乏味，指定所有的模板参数
```
目前为止我们已经提到没有显式指定模板参数和全部显式指定模板参数的情况.
还有另外一种方法: 仅显式指定第一个参数，其余参数允许推导。
**一般而言，需要显式指定所有的模板参数，直到最后一个参数可以推导为止。**
所以，对于上面的例子，需要改变模板参数的顺序，然后调用的人只需要指定返回值的类型:
```C++
template<typename RT, typename T1, typename T2>
RT max(T1 a, T2 b);

::max<double>(4. 7.2);  // OK，return type is double, T1, T2是推导的
```

#### 推导返回类型
如果返回类型依赖模板参数，最简单也是最好的方法是让编译器局定返回类型。
1. 使用auto

```C++
template<typename T1, typename T2>
auto max(T1 a, T2 b)
{
    return b < a ? a : b;
}
```
这种使用auto的方式表示返回值(没有对应的**尾随返回值类型trailing return type**)，表明返回值类型需要从函数体中的返回语句推导
在C++11中使用 尾随返回值类型语法可以通过使用`operator?:`来声明返回值类型
```C++
template<typename T1, typename T2>
auto max (T1 a, T2 b) -> decltype(b<a?a:b)
{
    return b < a ? a : b;
}

// 声明部分
template<typename T1, typename T2>
auto max (T1 a, T2 b) -> decltype(b<a?a:b);
```
注意声明部分，在编译时，编译器使用调用参数a, b的三元操作符`operator?:`来决定返回类型。
实现部分不一定要完全匹配，实际上，在`operator?:`中，是用true作为条件也是可以的:
```C++
template<typename T1, typename T2>
auto max (T1 a, T2 b) -> decltype(true?a:b);
```
这种处理有个缺点:当参数T为引用时，就会出现问题。为此，因应该使用`decay`:

```C++
template<typename T1, typename T2>
auto max(T1 a, T2 b) -> typename std::decay<decltype(true?a:b)>::type
{
    return b < a ? a : b;
}
```
这里使用了类型萃取函数`std::decay`,通过`内部成员type`返回结果,因为内部成员type是一种类型, 所以需要使用typename来访问。

**注意auto类型的初始化，总是`decay`的,当auto使用在返回值中也不例外。**
```C++
int i = 42;
int const& ir = i;  // ir refers to i
auto a = ir;        // a is declared as new object of type int, decay去除了const和reference
```

#### 返回值作为公共类型
`std::common_type<T1, T2>`

### 1.4 默认模板实参default template arguments
可以为模板参数定义默认值，这些值叫做默认模板实参, 可以用于任意类型的模板。
为RT指定默认值
```C++
template<typename T1, typename T2,
         typename RT = std::decay_t<decltype(true ? T1() : T2())>>
RT max (T1 a, T2 b)
{
    return b < a ? a : b;
}
```
同样，也可以使用`std::common_type`的类型萃取来指定返回值的默认值
```C++
template<typename T1, typename T2,
         typename RT = std::common_type_t<T1,T2>>
RT max(T1 a, T2 b)
{
    return b < a ? : b;
}
```
### 1.5 重载函数模板
就像普通函数一样，函数模板也可以重载。

```C++
// maximum of two int values:
int max(int a, int b)
{
    return b < a ? a : b; 
}

// maximum of two values of any type:
template<typename T>
T max(T a, T b)
{
    return b < a ? a : b; 
}
int main() 
{
    ::max(7, 42);       // calls the nontemplate for two ints
    ::max(7.0, 42.0);   // calls max<double> (by argument deduction)
    ::max(’a’, ’b’);    // calls max<char> (by argument deduction)
    ::max<>(7, 42);     // calls max<int> (by argument deduction)
    ::max<double>(7, 42);   // calls max<double> (no argument deduction)
    ::max(’a’, 42.7);       // calls the nontemplate for two ints
}
```

注意上述重载，参数部分是按值传递的。
如果按引用传递参数实现max，并以按值传递的C-string来重载的话，则会出现一个运行时错误
```C++
#include <cstring>

// maximum of two values of any type (call-by-reference)
template<typename T>
T const& max (T const& a, T const& b)
{
    return b<a?a:b; 
}

// maximum of two C-strings (call-by-value)
char const* max (char const* a, char const* b)
{
    return  std::strcmp(b,a) < 0  ? a : b;
}

// maximum of three values of any type (call-by-reference)
template<typename T>
T const& max (T const& a, T const& b, T const& c)
{
   return max (max(a,b), c);    // error if max(a,b) uses call-by-value
}

int main ()
{
   auto m1 = ::max(7, 42, 68);
   char const* s1 = "frederic";
   char const* s2 = "anica";
   char const* s3 = "lucas";
   auto m2 = ::max(s1, s2, s3); // run-time ERROR
}
```
因为max(a,b)会创建一个本地临时变量，但这个临时变量在return语句完成时会立马失效，所以引用这个临时变量会造成dangling reference的问题。