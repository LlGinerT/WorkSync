package com.synctech.worksync.ui.screens.detailPanel

//import com.synctech.worksync.ui.models.DetailUIModel
//
//@Composable
//fun WorkDetailScreen(work: DetailUIModel) {
//    val context = LocalContext.current
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(MaterialTheme.colorScheme.background)
//            .padding(16.dp),
//        verticalArrangement = Arrangement.spacedBy(16.dp)
//    ) {
//        Text(
//            text = "Detalles del Trabajo",
//            style = MaterialTheme.typography.headlineSmall,
//            fontWeight = FontWeight.Bold
//        )
//
//        Card(
//            shape = RoundedCornerShape(16.dp),
//            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
//            colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F9F9)),
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Column(modifier = Modifier.padding(16.dp)) {
//                DetailRow(title = "Trabajo", value = work.jobName)
//                DetailRow(title = "Cliente", value = work.clientName)
//                DetailRow(title = "Descripción", value = work.description)
//                DetailRow(title = "Dirección", value = work.address)
//            }
//        }
//
//        ElevatedButton(
//            onClick = {
//                val uri = Uri.parse("https://www.google.com/maps/search/?api=1&query=${Uri.encode(work.address)}")
//                val intent = Intent(Intent.ACTION_VIEW, uri)
//                context.startActivity(intent)
//            },
//            modifier = Modifier.align(Alignment.CenterHorizontally)
//        ) {
//            Icon(Icons.Filled.LocationOn, contentDescription = null)
//            Spacer(modifier = Modifier.width(8.dp))
//            Text("Ver en Google Maps")
//        }
//
//        Image(
//            painter = painterResource(id = R.drawable.map),
//            contentDescription = "Mapa del trabajo",
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(200.dp)
//                .padding(top = 8.dp)
//                .clip(RoundedCornerShape(12.dp))
//        )
//    }
//}
//
//@Composable
//fun DetailRow(title: String, value: String) {
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
//@Preview(showBackground = true)
//@Composable
//fun WorkDetailScreenPreview() {
//    val work = DetailUIModel(
//        jobName = "Instalación de Cableado",
//        clientName = "Juan Pérez",
//        description = "Instalación de cableado de fibra óptica en el edificio.",
//        address = "Calle de Alcalá, Madrid",
//        assignedTo = null
//    )
//    WorkDetailScreen(work = work)
//}
