package com.synctech.worksync.ui.screens.detailPanel

//
//@Composable
//fun JobDetailScreen(jobDetailViewModel: JobDetailViewModel, jobId: String) {
//
//    val context = LocalContext.current
//    val uiState by jobDetailViewModel.uiState.collectAsState()
//    val job = uiState.job
//
//    LaunchedEffect(jobId) {
//        jobDetailViewModel.loadWorkDetail(jobId)
//    }
//
//    JobDetailBackground {
//        when {
//            uiState.showLoadingIndicator -> {
//                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//                    CircularProgressIndicator()
//                }
//            }
//
//            uiState.errorMessage != null -> {
//                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//                    Text(
//                        text = uiState.errorMessage ?: "Error desconocido",
//                        color = MaterialTheme.colorScheme.error,
//                        style = MaterialTheme.typography.bodyLarge
//                    )
//                }
//            }
//
//            job != null -> {
//                JobDetailContent(job = job, context = context)
//            }
//        }
//
//    }
//}
//
//@Composable
//private fun JobDetailBackground(content: @Composable () -> Unit) {
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(MaterialTheme.colorScheme.background)
//    ) {
//        content()
//    }
//}
//
//@Composable
//private fun DetailRow(title: String, value: String) {
//    Column(modifier = Modifier.padding(vertical = 4.dp)) {
//        Text(
//            text = title,
//            style = MaterialTheme.typography.labelSmall.copy(color = Color.Gray, fontSize = 12.sp)
//        )
//        Text(
//            text = value,
//            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium),
//            maxLines = 2,
//            overflow = TextOverflow.Ellipsis
//        )
//    }
//}
//
//
//@Composable
//private fun JobDetailContent(
//    job: JobUiModel, context: Context
//) {
//    Box {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp),
//            verticalArrangement = Arrangement.spacedBy(16.dp)
//        ) {
//            Text(
//                text = "Detalles del Trabajo",
//                style = MaterialTheme.typography.headlineSmall,
//                fontWeight = FontWeight.Bold
//            )
//
//            Card(
//                shape = RoundedCornerShape(16.dp),
//                elevation = cardElevation(defaultElevation = 6.dp),
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Column(modifier = Modifier.padding(16.dp)) {
//                    DetailRow(title = "Trabajo", value = job.jobName)
//                    DetailRow(title = "Cliente", value = job.clientName)
//                    DetailRow(title = "Descripción", value = job.description)
//                    DetailRow(title = "Dirección", value = job.address)
//                }
//            }
//
//            ElevatedButton(
//                onClick = {
//                    val uri =
//                        Uri.parse("https://www.google.com/maps/search/?api=1&query=${Uri.encode(job.address)}")
//                    val intent = Intent(Intent.ACTION_VIEW, uri)
//                    context.startActivity(intent)
//                }, modifier = Modifier.align(Alignment.CenterHorizontally)
//            ) {
//                Icon(Icons.Filled.LocationOn, contentDescription = null)
//                Spacer(modifier = Modifier.width(8.dp))
//                Text("Ver en Google Maps")
//            }
//
//            Image(
//                painter = painterResource(id = R.drawable.map),
//                contentDescription = "Mapa del trabajo",
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(200.dp)
//                    .padding(top = 8.dp)
//                    .clip(RoundedCornerShape(12.dp))
//            )
//        }
//    }
//}
//
