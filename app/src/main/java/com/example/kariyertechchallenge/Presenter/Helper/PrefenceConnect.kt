package com.example.kariyertechchallenge.Presenter.Helper

import android.content.Context
import java.io.*

object PrefenceConnect {
    //Yazılan datanın okunması
    fun readDataToInternal(fileName: String, context: Context): Any? {
        try {
            val fis = context.openFileInput(fileName)
            if (fis == null) {
                return null
            }
            val inputstream = ObjectInputStream(fis)
            return inputstream.readObject()
        } catch (e: Exception) {
            //e.printStackTrace();
        }

        return null
    }
    //Yazılan datanın silinmesi
    fun deleteDataToInternal(fileName: String, context: Context): Boolean {
        try {
            //String path = getFilesDir().getAbsolutePath() + "/" + fileName;
            val file = File(context.getFilesDir(), fileName)
            if (file.exists()) return file.delete()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return false
    }
    //Local'e data yazma işlemini gerçekleştirir.
    fun writeDataToInternal(fileName: String, obj: Any,context: Context) {
        /*  try {
              val fos = App.getContext()?.openFileOutput(fileName, Context.MODE_PRIVATE)
              val os = ObjectOutputStream(fos)
              os.writeObject(obj)
              os.close()
          } catch (e: Exception) {
              e.printStackTrace()
          }
  */
        val fos: FileOutputStream
        try {
            fos =  context.openFileOutput(fileName, Context.MODE_PRIVATE)


            val oos = ObjectOutputStream(fos)
            oos.writeObject(obj)
            oos.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
}