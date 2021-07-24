# RepoCommitTimeline
Repo Commit Timeline is an Android app for me to practice using REST API for Github, by suing Retrofit and MVVM architecture.

## Getting Started

## Images
|||

## Libraries Used
 - [Navigation component & Safe args](https://developer.android.com/jetpack/androidx/releases/navigation) - Navigation is a framework for navigating between 'destinations' within an Android application that provides a consistent API whether destinations are implemented as Fragments, Activities, or other components. And Safe Args that generates simple object and builder classes for type-safe navigation and access to any associated arguments. Safe Args is strongly recommended for navigating and passing data, because it ensures type-safety.
 - [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - Hilt is a dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in your project. Doing manual dependency injection requires you to construct every class and its dependencies by hand, and to use containers to reuse and manage dependencies.
 - [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.
 - [Github REST API](https://docs.github.com/en/rest) - You can use the GitHub REST API to create calls to get the data you need to integrate with GitHub.
 - [Kotlin coroutines](https://developer.android.com/kotlin/coroutines) - A coroutine is a concurrency design pattern that you can use on Android to simplify code that executes asynchronously. Coroutines were added to Kotlin in version 1.3 and are based on established concepts from other languages.
 - [Picasso](https://square.github.io/picasso/) - A powerful image downloading and caching library for Android.

## Notes

If you want to compile and run this project, don't forget to create a file (called keys.properties) in the root of project containing [Github's API keys](https://github.com/settings/applications/new):

    GithubClientId="YOUR_ID"
    GithubClientSecret="YOUR_SECRET"
    Token="YOUR_PRIVATE_AUTHENTICATED_TOKEN"

License
-------

    Copyright 2021 Romi

    Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0
    Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.