# ⚡ ThreadForge

### Inside Java Concurrency — Building, Comparing & Benchmarking TCP Servers

**ThreadForge** is a hands-on Java networking project that explores how different concurrency models handle multiple client connections. It implements and benchmarks three TCP server architectures to understand their behavior under increasing client loads.

The goal is simple: **Understand how threads work, measure their performance, and discover the trade-offs behind different server architectures.**

---

## 🧭 Project Overview

ThreadForge explores three approaches to handling concurrent client connections:

| Architecture | Approach |
|---|---|
| Single-Threaded | Handles one client connection at a time |
| Multithreaded | Creates a separate thread for each client |
| Thread Pool | Uses a fixed number of worker threads to process client tasks |

Each implementation runs as a TCP server, allowing performance comparisons under controlled testing conditions.

---

## 🏗️ System Architecture

```text
                    CLIENT REQUESTS
                           |
                           ▼
                    TCP CONNECTION
                           |
             ┌─────────────┼─────────────┐
             ▼             ▼             ▼
       SINGLE-THREAD   MULTITHREADED   THREAD POOL
          SERVER          SERVER         SERVER
             |             |             |
         One at a       One thread    Fixed worker
         a time         per client    thread pool
             |             |             |
             └─────────────┼─────────────┘
                           ▼
                    SERVER RESPONSE
                           |
                           ▼
                    CLIENT RECEIVES
```

---

## 🧵 The Three Concurrency Models

### 01. Single-Threaded Server

- Uses a single execution thread to handle client connections.
- Processes connections sequentially.
- Simple architecture with minimal thread-management overhead.
- Can become a bottleneck when multiple clients connect simultaneously.

### 02. Multithreaded Server

- Creates a new thread for each accepted client connection.
- Allows multiple clients to be handled concurrently.
- Improves concurrency but introduces thread-creation and resource-management overhead.
- Large numbers of simultaneous connections can put pressure on system resources.

### 03. Thread Pool Server

- Uses Java's `ExecutorService` and `Executors.newFixedThreadPool()`.
- Reuses a fixed number of worker threads.
- Queues incoming tasks when all workers are busy.
- Reduces repeated thread creation and provides control over worker-thread count.

---

## 🧪 Performance Benchmarking Lab

**Benchmarking Tool:** Apache JMeter 5.6.3

ThreadForge uses JMeter's TCP Sampler to generate client connections and measure server response behavior.

### Test Configuration

| Parameter | Configuration |
|---|---|
| Protocol | TCP |
| Server | localhost |
| Port | 8010 |
| Loop Count | 1 |
| Connection Reuse | Disabled |
| Testing Tool | Apache JMeter |

### Benchmark Metrics

| Metric | Description |
|---|---|
| Samples | Total requests executed |
| Average | Average response time |
| Minimum | Lowest response time |
| Maximum | Highest response time |
| Error % | Percentage of failed samples |
| Throughput | Requests processed per second |

---

## 📊 Experimental Results

The following are selected observations from the experiments conducted during development.

### Single-Threaded Server

| Concurrent Users | Average | Maximum | Errors | Throughput |
|---:|---:|---:|---:|---:|
| 500 | 4 ms | 49 ms | 0% | 8.3/sec |
| 5,000 | 11 ms | 564 ms | 0% | 81.7/sec |
| 10,000 | 386 ms | 8,093 ms | 6.76% | 150/sec |

### Thread Pool Server — 6,000 Clients

| Pool Size | Test Run | Average | Maximum | Errors | Throughput |
|---:|---|---:|---:|---:|---:|
| 10 | Initial | 6 ms | 472 ms | 0% | 97.4/sec |
| 25 | First Run | 42 ms | 3,278 ms | 2.83% | 98.2/sec |
| 25 | Repeat Run | 3 ms | 330 ms | 0% | 98.9/sec |

**Important:** Benchmark results are experimental and can vary with system load, available resources, and test conditions. These observations do not establish that a particular pool size is universally faster.

---

## 🔍 Engineering Observations

Some important concepts explored during this project:

- Thread creation has resource and scheduling overhead.
- Thread-per-client architectures can become resource-intensive under high concurrency.
- A fixed thread pool limits the number of worker threads.
- Tasks can wait in the executor's queue when all workers are occupied.
- Increasing pool size does not automatically improve throughput or eliminate errors.
- Average response time and throughput must be evaluated together.

---

## 🛠️ Tech Stack

- **Language:** Java
- **Networking:** Java Socket API
- **Concurrency:** Java Threads
- **Thread Management:** ExecutorService
- **Benchmarking:** Apache JMeter 5.6.3
- **Version Control:** Git & GitHub

---

## 📁 Project Structure

```text
ThreadForge/
│
├── SingleThreaded/
│   ├── Server.java
│   └── Client.java
│
├── Multithreaded/
│   ├── Server.java
│   └── Client.java
│
├── ThreadPool/
│   └── Server.java
│
├── JMeter/
│   ├── SingleThreaded_Test_01.jmx
│   ├── summary-results.jtl
│   ├── view-results.jtl
│   └── view-results-in-tree.jtl
│
├── .gitignore
└── README.md
```

---

## 🚀 Getting Started

### Prerequisites

- Java Development Kit (JDK)
- Apache JMeter 5.6.3
- Git

### Clone the Repository

```bash
git clone https://github.com/shubhamjadhav-94/ThreadForge.git
```

```bash
cd ThreadForge
```

### Run the Single-Threaded Server

```bash
cd SingleThreaded
javac Server.java
java Server
```

### Run the Multithreaded Server

```bash
cd Multithreaded
javac Server.java
java Server
```

### Run the Thread Pool Server

```bash
cd ThreadPool
javac Server.java
java Server
```

**Note:** All server implementations use port `8010`. Stop the currently running server before starting another implementation.

---

## 🎯 Learning Objectives

Through ThreadForge, this project explores:

- TCP client-server communication.
- Java Socket Programming.
- Single-threaded vs multithreaded execution.
- Thread-per-client architecture.
- ExecutorService and fixed thread pools.
- Concurrency and resource management.
- Performance benchmarking using Apache JMeter.
- Interpreting latency, throughput, and error rates.

---

## 🔮 Future Improvements

- Experiment with different thread pool sizes.
- Implement server-side file reading and transfer to clients.
- Benchmark different file sizes.
- Compare throughput under I/O-intensive workloads.
- Explore thread pool configuration and task queue behavior.
- Add more detailed performance visualizations.

---

## 👨‍💻 Author

**Shubham Jadhav**

GitHub: [@shubhamjadhav-94](https://github.com/shubhamjadhav-94)

---

<p align="center">
  <b>THREADFORGE — Explore Threads. Measure Performance. Understand Concurrency.</b>
</p>