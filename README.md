<div align="center">

# 🛍️ ShopAThing
### A Modern, Scalable Android E-Commerce Application

![Android](https://img.shields.io/badge/Platform-Android-3DDC84?style=flat&logo=android)
![Kotlin](https://img.shields.io/badge/Language-Kotlin-7F52FF?style=flat&logo=kotlin)
![Jetpack Compose](https://img.shields.io/badge/UI-Jetpack%20Compose-4285F4?style=flat&logo=jetpackcompose)
![Firebase Auth](https://img.shields.io/badge/Auth-Firebase-FFCA28?style=flat&logo=firebase&logoColor=black)
![License](https://img.shields.io/badge/License-MIT-blue?style=flat)

<a href="https://github.com/SilentFURY-x/ShopAThing-App/releases/download/v1.0.0/ShopAThing-v1.0.0.apk">
  <img src="https://img.shields.io/badge/Download-APK-3DDC84?style=for-the-badge&logo=android&logoColor=white" alt="Download APK"/>
</a>

</div>

---

## 📱 About The Project

**ShopAThing** is a feature-rich shopping application engineered with **Clean Architecture** and **Modern Android Development (MAD)** standards.

Unlike typical demo apps, ShopAThing focuses on **scalability, performance, and user experience**. It features a robust offline caching layer, server-side search optimization, and a fluid, animated UI that adapts to user preferences.

> **"Not just a demo—a foundation for a real startup product."**

### ✨ Why this stands out?
* **Performance First:** Handles lists of 1000+ items seamlessly using **Paging 3**.
* **Smart Network Usage:** Implements **Debouncing** to minimize API calls during search.
* **Offline Capable:** The cart persists across app restarts using **Room Database**.
* **Reactive UI:** Fully built with **Jetpack Compose** and **Material 3**.

---

## 🚀 Key Features

### 🔍 Discovery & Navigation
* **Infinite Scrolling:** Smooth pagination loads products effortlessly as you scroll.
* **Smart Search:** Server-side search with **500ms debounce** to reduce network load.
* **Dynamic Filtering:** Filter products by category using reactive chips.
* **Shimmer Loading:** Skeleton animations provide a polished loading experience.

### 🛒 Shopping Experience
* **Persistent Cart:** Add items, update quantities, or remove products. Data is saved locally via **Room**, so your cart is never lost.
* **Real-Time Badge:** A dynamic notification badge on the cart icon updates instantly as you shop.
* **Seamless Checkout:** Animated checkout flow using **Lottie Animations** for visual feedback.

### 🎨 User Experience (UX)
* **Dark Mode:** A global, persistent Dark Theme toggle (saved via **DataStore**) that remembers your preference.
* **Visual Polish:** Dynamic star rating bars, error handling, and empty state animations.
* **Share Functionality:** Native Android share sheet integration to share product details.

---

## 🛠️ Tech Stack & Architecture

The app follows strict **Clean Architecture (MVVM)** principles to ensure separation of concerns and testability.

| Layer | Component | Technology Used |
| :--- | :--- | :--- |
| **🎨 UI** | **Toolkit** | Jetpack Compose (Material 3) |
| | **Images** | Coil (Async Loading) |
| | **Animations** | Lottie & Shimmer |
| **🧠 Logic** | **Architecture** | MVVM + Clean Architecture |
| | **DI** | Dagger Hilt |
| | **Async** | Coroutines & Flows |
| **💾 Data** | **API** | Retrofit + OkHttp |
| | **Local DB** | Room (SQLite) |
| | **Prefs** | DataStore |
| **☁️ Cloud** | **Auth** | Firebase Authentication |

---

## 📂 Architecture Overview
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
```
---

## 📸 Screenshots

<div align="center">
  
| **Home (Dark Mode)** | **Product Details** | **Smart Cart** |
|:---:|:---:|:---:|
| <img src="screenshots/home_dark.png" width="220" /> | <img src="screenshots/detail.png" width="220" /> | <img src="screenshots/cart.png" width="220" /> |

| **Search & Filter** | **Success Animation** | **Empty State** |
|:---:|:---:|:---:|
| <img src="screenshots/search.png" width="220" /> | <img src="screenshots/success.gif" width="220" /> | <img src="screenshots/empty.gif" width="220" /> |

</div>

---

## ⚡ Setup & Installation
**Download the App:**
* Click below to download the latest stable version (v1.0.0) for your Android device:
* 👉 **[Download ShopAThing-v1.0.0.apk](https://github.com/SilentFURY-x/ShopAThing-App/releases/download/v1.0.0/ShopAThing-v1.0.0.apk)**
    
**OR**

1.  **Clone the repository:**
    ```bash
        git clone https://github.com/SilentFURY-x/ShopAThing-App.git
    ```
2.  **Open in Android Studio:**
     * Requires Android Studio Hedgehog or newer.

3.  **Firebase Setup:**
     * This project uses Firebase Auth.
     * Add your own google-services.json file to the /app directory.

4.  **Build & Run:**
     * Sync Gradle and run on an Emulator or Physical Device.

---

## 🤝 Contributing

Contributions are what make the open-source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.
1.  Fork the Project
2.  Create your Feature Branch:
    ```bash
    git checkout -b feature/AmazingFeature
    ```
3.  Commit your Changes:
    ```bash
    git commit -m 'Add some AmazingFeature'
    ```
4.  Push to the Branch:
    ```bash
    git push origin feature/AmazingFeature
    ```
5.  Open a Pull Request

---

## 📄 License

Distributed under the MIT License. See `LICENSE` for more information.

---

## 👨‍💻 Author
<div align="center">

**Arjun Tyagi**

[![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)](https://github.com/SilentFURY-x)
[![LinkedIn](https://img.shields.io/badge/linkedin-%230077B5.svg?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/arjun-tyagi-84b1b5328/)

</div>

---

<p align="center">
  <i>Built with ❤️ using Kotlin and Jetpack Compose.</i>
</p>
