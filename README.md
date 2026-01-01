# ShopAThing 🛍️

**ShopAThing** is a modern, scalable Android E-commerce application built with **Jetpack Compose** and **Clean Architecture**. It features real-time product pagination, server-side filtering, offline-first cart management, and robust authentication.

## 🚀 Features Implemented

This project completes **Level 1, Level 2, and Level 3** requirements for the assignment, plus additional "Senior-Level" enhancements.

### 🌟 Level 3: Advanced Logic & Architecture (Completed)
- **Infinite Scrolling (Pagination):** Implemented using **Paging 3**. Seamlessly loads hundreds of products from the *DummyJSON* API without performance lag.
- **Server-Side Search & Filtering:** Custom `PagingSource` implementation that handles search queries and category filtering directly from the server (Debounced search input for performance).
- **Persistent Cart (Offline-First):** Uses **Room Database** to save cart items. Data persists even if the app is killed.
    - Smart "Upsert" logic (increments quantity instead of duplicating items).
    - Auto-remove item when quantity hits zero.
- **Checkout Flow:** Clears local database and provides user feedback upon order completion.
- **UI Bonus (Dark Mode):** Persistent Dark Mode toggle using **DataStore Preferences**. Remembers user choice across app restarts.

### 🔒 Level 2: Auth & API (Completed)
- **Firebase Authentication:** Full Email/Password Login and Signup flow.
- **Smart Splash Screen:** Checks `FirebaseAuth` state on launch to auto-redirect users to Home (if logged in) or Login (if new).
- **Network Layer:** Built with **Retrofit** & **OkHttp**, using standard DTOs and Mappers.

### 🎨 Level 1: UI Foundation (Completed)
- **Modern UI:** 100% Jetpack Compose implementation using Material 3 guidelines.
- **components:** Reusable `ProductCard`, `SearchBar`, and `CartItem` components.
- **Navigation:** Type-safe Jetpack Compose Navigation.

---

## 🛠️ Tech Stack & Libraries

* **Language:** Kotlin
* **UI Toolkit:** Jetpack Compose (Material 3)
* **Architecture:** Clean Architecture (MVVM + Repository Pattern)
* **Dependency Injection:** Dagger Hilt
* **Network:** Retrofit + Gson
* **Async/Concurrency:** Coroutines + Flow
* **Database:** Room (SQLite)
* **Pagination:** Paging 3 (RemoteMediator / PagingSource)
* **Image Loading:** Coil
* **Local Storage:** DataStore Preferences
* **Authentication:** Firebase Auth

---

## 📂 Project Structure

The project follows strict **Clean Architecture** principles to ensure separation of concerns:

```text
com.fury.shopathing
├── data                // Data Layer (API, Room DB, Repositories)
│   ├── local           // Room Entities & DAOs
│   ├── remote          // Retrofit Interfaces & DTOs
│   └── repository      // Implementation of Domain Interfaces
├── domain              // Domain Layer (Pure Kotlin)
│   ├── model           // Data Classes (Product, CartItem)
│   └── repository      // Interfaces
├── di                  // Dependency Injection (Hilt Modules)
├── presentation        // UI Layer (ViewModels, Composables)
│   ├── components      // Reusable UI widgets
│   ├── screens         // (Home, Detail, Cart, Auth)
│   └── theme           // Material 3 Theme & Type
└── utils               // Constants & Helper Classes
