#CHANGELOG

version 0.9.7:
- Reworked `MovieDetailsFragment`. With `id` passed to the fragment, it now fetches movie by id from `DataSource` via `MainUseCase` & `MainRepository`
- Error handling in `RemoteDataSource` has been reworked. Now it is handled by `ErrorResponse` class which is used to parse `errorBody()` of retrofit response.
- `LatestMovie` class has been trimmed down. Removed all, unnecessary fields.
- Added `HomeFragment` implementation. It is now used to display Latest Movie added to TMDB.
- General code clean up