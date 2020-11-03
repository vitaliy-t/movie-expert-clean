#Movie Expert using Clean Architecture and MVVM together.

version 0.9.9:
- Changed naming of `MainRepository` & `MainUseCase` to `MoviesRepository` & `MoviesUseCase`
- Removed `FirebaseAuth` methods from `MoviesRepository` & `MoviesUseCase`. Now they're moved to the corresponding ViewModel that uses them.
- Added interface for `MoviesRepository`.
- Movie `DataSourceModule` di from domain to data since it fits there better.
- Added Domain's own di module where `MoviesRepository` is bound.
- Code Clean up.