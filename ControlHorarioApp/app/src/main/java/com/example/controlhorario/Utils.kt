package com.example.controlhorario
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.FileProvider
import java.io.File

fun mostrarNotificacion(context: Context, titulo: String, mensaje: String) {
    val channelId = "control_horario_channel"
    val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(channelId, "Control Horario", NotificationManager.IMPORTANCE_HIGH)
        notificationManager.createNotificationChannel(channel)
    }

    val builder = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.drawable.ic_notification)
        .setContentTitle(titulo)
        .setContentText(mensaje)
        .setPriority(NotificationCompat.PRIORITY_HIGH)

    notificationManager.notify(System.currentTimeMillis().toInt(), builder.build())
}

fun exportarCSV(context: Context, registros: List<String>) {
    val fileName = "registros_${System.currentTimeMillis()}.csv"
    val file = File(context.getExternalFilesDir(null), fileName)
    file.bufferedWriter().use { out ->
        out.write("Tipo,Fecha,Hora Real,Hora Ajustada,Turno
")
        registros.forEach { out.write("$it
") }
    }

    val uri = FileProvider.getUriForFile(context, "${context.packageName}.provider", file)
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/csv"
        putExtra(Intent.EXTRA_STREAM, uri)
    }
    context.startActivity(Intent.createChooser(intent, "Compartir CSV"))
}
