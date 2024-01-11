- [C++ Concurency In Action Second Edition](#c-concurency-in-action-second-edition)
- [1. Hello, world of C++ Concurency](#1-hello-world-of-c-concurency)
  - [1.1 What is concurency?](#11-what-is-concurency)
    - [1.1.1 Concurrency in computer systems](#111-concurrency-in-computer-systems)
    - [1.1.2 Approaches to concurrency](#112-approaches-to-concurrency)
      - [CONCURRENCY WITH MULTIPLE PROCESSES](#concurrency-with-multiple-processes)
      - [CONCURRENCY WITH MULTIPLE THREADS](#concurrency-with-multiple-threads)
    - [1.1.3 Concurrency vs. parallelism](#113-concurrency-vs-parallelism)
  - [1.2 Why use concurrency?](#12-why-use-concurrency)
    - [1.2.1 Using concurrency for separation of concerns](#121-using-concurrency-for-separation-of-concerns)
    - [1.2.1 Using concurrency for performance: task and data parallelism](#121-using-concurrency-for-performance-task-and-data-parallelism)
- [2. Managing threads](#2-managing-threads)
    - [launch a thread](#launch-a-thread)
- [3. Sharing data between thread](#3-sharing-data-between-thread)
  - [3.1 Problems with sharing data between threads](#31-problems-with-sharing-data-between-threads)
    - [3.1.1 Race conditions](#311-race-conditions)
    - [3.1.2 Avoiding problematic race conditions](#312-avoiding-problematic-race-conditions)
  - [3.2 Protecting shared data with mutexes](#32-protecting-shared-data-with-mutexes)
    - [3.2.1 Using mutexes in C++](#321-using-mutexes-in-c)
    - [3.2.2 Structuring code for protecting shared data](#322-structuring-code-for-protecting-shared-data)
    - [3.2.3 Spotting race conditions inherent in interfaces](#323-spotting-race-conditions-inherent-in-interfaces)
    - [3.2.4 Deadlock: the problem and a solution](#324-deadlock-the-problem-and-a-solution)
    - [3.2.5 Further guidelines for avoiding deadlock](#325-further-guidelines-for-avoiding-deadlock)
    - [3.2.6 Flexible locking with std::unique\_lock](#326-flexible-locking-with-stdunique_lock)
    - [3.2.7 Transferring mutex ownership between scopes](#327-transferring-mutex-ownership-between-scopes)
    - [3.2.8 Locking at an appropriate granularity](#328-locking-at-an-appropriate-granularity)
  - [3.3 Alternative facilities for protecting shared data](#33-alternative-facilities-for-protecting-shared-data)
    - [3.3.1 Protecting shared data during initialization](#331-protecting-shared-data-during-initialization)
    - [3.3.2 Protecting rarely updated data structures](#332-protecting-rarely-updated-data-structures)
    - [3.3.3 Recursive locking](#333-recursive-locking)
- [4. Synchronizing concurrent operations](#4-synchronizing-concurrent-operations)
  - [4.1 Waiting for an event or other condition](#41-waiting-for-an-event-or-other-condition)
  - [4.2 Waiting for one-off events with futures](#42-waiting-for-one-off-events-with-futures)
    - [4.2.1 Returning values from background tasks](#421-returning-values-from-background-tasks)
    - [4.2.2 Associating a task with a future](#422-associating-a-task-with-a-future)


# C++ Concurency In Action Second Edition

# 1. Hello, world of C++ Concurency
## 1.1 What is concurency?
At the simplest and most basic level, concurrency is about two or more separate activi- ties happening at the same time.

### 1.1.1 Concurrency in computer systems
- task switching
- hardware concurency
- context switch
  - 1. save CPU state and instruction pointer for currently runing task
  - 2. work out which task to switch to and reload CPU state for the task being switched to
  - 3. **load** the memory for **instructions and datas** for the new task into **cache**
- multiplecore and multipleprocessor
  - multiplecore in single processor: one single-core processor
  - many multicore processors
- hardware thread

### 1.1.2 Approaches to concurrency
#### CONCURRENCY WITH MULTIPLE PROCESSES

#### CONCURRENCY WITH MULTIPLE THREADS

### 1.1.3 Concurrency vs. parallelism

People talk about **parallelism** when their primary concern is taking advantage of the available hardware to increase the performance of bulk data processing, whereas people talk about **concurrency** when their primary concern is sepa- ration of concerns, or responsiveness.

## 1.2 Why use concurrency?

**two main reasons** to use concurrency in an application:
- separation of concerns
- performance.

### 1.2.1 Using concurrency for separation of concerns

Not only does it have to read the data from the disk, decode the images and sound, and send them to the graphics and sound hardware in a timely fashion so the DVD plays without glitches, but it must also take input from the user, such as when the user clicks Pause or Return To Menu, or even Quit.

DVD Player:
- read the data from disk
- decode the image and sound
- send them to the graphic and sound hardware in timely
- response the input from user, suck as when the user clicks Pause or Return To Menu, or even Quit.

By **using multi-threading to separate these concerns**, the user interface code and DVD playback code no longer have to be so closely intertwined; one thread can handle the user interface and another the DVD playback.


### 1.2.1 Using concurrency for performance: task and data parallelism
There are two ways to use concurrency for performance.
- task parallelism
The first, and most obvious, is to divide a single task into parts and run each in parallel, reducing the total runtime.
This is task parallelism.

- data parallelism: each thread performs the same operation on different parts of the data
The divisions may be either in terms of processing—one thread performs one part of the algorithm while another thread performs a different part, or in terms of data, each thread performs the same operation on different parts of the data. This latter approach is called data parallelism.

- available parallelism: 利用 hardware threada
The second way to use concurrency for performance is to use the available parallelism to solve bigger problems.

Although this is an application of **data parallelism**, by performing the same operation on multiple sets of data concurrently, there’s a different focus. It still takes the same amount of time to process one chunk of data, **but now more data can be processed in the same amount of time.**
此时的数据并行时在相同的时间内处理更多的数据了。

# 2. Managing threads
thread basic:
- launching a thread
- waiting for it to finish
- running it in the background
- passing additional parameters to the thread function when it's launched 
- transfer owner- ship of a thread from one std::thread object to another. 
- choosing the number of threads to use and identifying particular threads

Every C++ program has at least one thread, which is started by the C++ runtime: the thread running `main()`.

### launch a thread

3 ways to launch a thread,
- function
- function object
- lambda expression

```c++
// function
void do_some_work();
std::thread my_thread(do_some_work);

// function object
class background_task
{
public:
    void operator()() const
    {
        do_something();
        do_something_else();
    }
};
background_task f;
std::thread my_thread(f);

// lambda expression
std::thread my_thread([]{
    do_something();
    do_something_else();
});

```


This isn’t a new problem even in single-threaded code it’s undefined behavior to access an object after it’s been destroyed
but the use of threads provides an additional opportunity to encounter such lifetime issues.

One common way to handle this scenario is to make the thread function self-contained and copy the data into the thread rather than sharing the data.

list the example in Listing2.1

```c++
void do_something(int i)
{
    if (i % 1000000 == 0)
        std::cout << "epoch " << i << "\n";
}

struct func
{
    int& i;
    func(int& i_):i(i_){}
    void operator()()
    {
        for(unsigned j=0;j<1000000;++j)
        {
            do_something(i*j);
        }
    }
};

void oops()
{
    int some_local_state = 12;
    func my_func(some_local_state);
    std::thread my_thread(my_func);
    // my_thread.detach();
    my_thread.join();
}

int main()
{
    oops();
    return 0;
}

```

Alternatively, you can ensure that the thread has completed execution before the function exits by **joining** with the thread.

两种方法解决new thread中访问已经释放的变量dangling reference or pointer
- copy data rather than reference data
- waiting the new thread finished by **join()**

But you still need to be wary of objects contain- ing pointers or references, such as in listing 2.1. In particular, it’s a bad idea to create a thread within a function that has access to the local variables in that function, unless the thread is guaranteed to finish before the function exits.

# 3. Sharing data between thread

- 排他锁 std::mutex
- 死锁以及如何避免死锁(几种不同的方法)
- 读写锁 std::shared_mutex, std::shared_timed_mutex
- 可重入锁 std::recursive_mutex



The ease with which data can be shared between multiple threads in a single process is not only a benefit, it can also be a big drawback. 
Incorrect use of shared data is one of the biggest causes of concurrency-related bugs.

## 3.1 Problems with sharing data between threads
If all shared data is read-only, there’s no problem, because the data read by one thread is unaffected by whether or not another thread is reading the same data.
If data is shared between threads, and **one or more threads start modifying the data**, there’s a lot of potential for trouble. In this case, you must take care to ensure that everything works out OK.

double-linked list -> race condition

### 3.1.1 Race conditions
In concurrency, a race condition is anything where the outcome depends on the relative ordering of execution of operations on two or more threads;

The C++ Standard also defines the term **data race** to mean the specific type of race condition that arises because of concurrent modification to a single object; 
data races cause the dreaded undefined behavior.

Problematic race conditions typically occur where completing an operation requires modification of two or more distinct pieces of data, such as the two link pointers in the example.
### 3.1.2 Avoiding problematic race conditions

1. protection mechanism
There are several ways to deal with problematic race conditions. The simplest option is to wrap your data structure with a **protection mechanism** to ensure that only the thread performing a modification can see the intermediate states where the invariants are broken.

2. lock-free programming
Another option is to modify the design of your data structure and its invariants so that modifications are done as a series of indivisible changes, each of which preserves the invariants. This is generally referred to as **lock-free programming** and is difficult to get right.

3. transaction
Another way of dealing with race conditions is to handle the updates to the data structure as a **transaction**, just as updates to a database are done within a transaction.

The most basic mechanism for protecting shared data provided by the C++ Standard is the **mutex**.

## 3.2 Protecting shared data with mutexes
Mutexes are the most general of the data-protection mechanisms available in C++, but they’re not a silver bullet.
Mutexes also come with their own problems in the form of a **deadlock** and protecting either too much or too little data.

### 3.2.1 Using mutexes in C++
### 3.2.2 Structuring code for protecting shared data
### 3.2.3 Spotting race conditions inherent in interfaces
### 3.2.4 Deadlock: the problem and a solution
### 3.2.5 Further guidelines for avoiding deadlock
### 3.2.6 Flexible locking with std::unique_lock
### 3.2.7 Transferring mutex ownership between scopes
### 3.2.8 Locking at an appropriate granularity

## 3.3 Alternative facilities for protecting shared data
### 3.3.1 Protecting shared data during initialization
### 3.3.2 Protecting rarely updated data structures
### 3.3.3 Recursive locking


# 4. Synchronizing concurrent operations

Chapter 4 builds on the facilities I’ve discussed for protecting shared data and
introduces the various mechanisms for synchronizing operations between threads in C++.
## 4.1 Waiting for an event or other condition

`std::condition_variable`

`std::unique_lock` and `std::lock_guard` difference in 
Listing 4.5 Full class definition of a thread-safe queue using condition variables

## 4.2 Waiting for one-off events with futures
The C++ Standard Library models this sort of one-off event with something called a *future*.

There are two sorts of futures in the C++ Standard Library, implemented as two class templates declared in the <future> library header: *unique futures* (`std::future<>`) and *shared futures* (`std::shared_future<>`).

These are modeled after `std::unique_ptr` and `std::shared_ptr`. An instance of `std::future` is the one and only instance that refers to its associated event, whereas multiple instances of `std::shared_future` may refer to the same event.

`std::future`

### 4.2.1 Returning values from background tasks
`std::future`
`std::async`

```c++
#include <future>
#include <iostream>
int find_the_answer_to_ltuae();
void do_other_stuff();
int main()
{
    std::future<int> the_answer = std::async(find_the_answer_to_ltuae);
    do_other_stuff();
    std::cout<<"The answer is "<<the_answer.get()<<std::endl;
}
```

### 4.2.2 Associating a task with a future

`std::packaged_task<>` ties a future to a function or callable object. 
When the `std:: packaged_task<>` object is invoked, it calls the associated function or callable object and makes the future *ready*, with the return value stored as the associated data.

This can be used as a building block for thread pools or other task management schemes, such as running each task on its own thread, or running them all sequentially on a particular background thread.