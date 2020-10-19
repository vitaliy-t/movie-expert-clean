#CHANGELOG

version 0.9.5:
- `LocalDS` and `RemoteDS` interfaces were merged into one: `DataSource`.
- Reworked DI for DataSource. Now using custom Annotations
- Added `MainRepository` layer for communication between `MainUseCase` and `DataSource`
- General code clean up.