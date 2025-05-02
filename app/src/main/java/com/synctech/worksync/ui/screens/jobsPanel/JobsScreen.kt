package com.synctech.worksync.ui.screens.jobsPanel

//
//@Composable
//private fun JobsBackground(content: @Composable () -> Unit) {
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(colorScheme.background)
//    ) {
//        content()
//    }
//}
//
//@Composable
//fun JobScreen(
//    viewModel: JobsViewModel, navController: NavController, modifier: Modifier = Modifier
//) {
//    val uiState by viewModel.uiState.collectAsState()
//
//    JobsBackground {
//        when {
//            uiState.showLoadingIndicator -> {
//                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//                    CircularProgressIndicator()
//                }
//            }
//
//            uiState.errorMessage != null -> {
//                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//                    Text(
//                        text = uiState.errorMessage ?: "Error desconocido",
//                        color = colorScheme.error,
//                        style = typography.bodyLarge
//                    )
//                }
//            }
//
//            else -> {
//                if (uiState.jobsList.isEmpty()) {
//                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//                        Text(
//                            text = "No hay trabajos disponibles.",
//                            color = colorScheme.onBackground,
//                            style = typography.bodyLarge
//                        )
//                    }
//                } else {
//                    LazyVerticalGrid(
//                        columns = GridCells.Adaptive(minSize = 150.dp),
//                        modifier = modifier
//                            .fillMaxSize()
//                            .padding(16.dp),
//                        contentPadding = PaddingValues(16.dp),
//                        horizontalArrangement = Arrangement.spacedBy(8.dp),
//                        verticalArrangement = Arrangement.spacedBy(8.dp)
//                    ) {
//                        items(uiState.jobsList) { job ->
//                            JobCard(job = job, onClick = {
//                                // navController.navigate("jobDetail/${job.workId}")
//                            })
//                        }
//                    }
//                }
//            }
//
//        }
//    }
//}





