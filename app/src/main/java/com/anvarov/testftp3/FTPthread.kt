package com.anvarov.testftp3

import android.widget.TextView
import org.apache.commons.net.ftp.FTP
import org.apache.commons.net.ftp.FTPClient
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStream

class FTPthread():Thread(){
    //767|1652&365|748
    var urisArr : File?=null
    var num :Int?=null
    var infoos : TextView?=null

    override fun run() {
        val server = "ftp1.oas-orb.ru"
        val port = 21
        val user = "00000000118334"
        val pass = "T08FZVqk"
        val ftpClient = FTPClient()
        try {
            ftpClient.connect(server, port)
            ftpClient.login(user, pass)
            ftpClient.enterLocalPassiveMode()
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE)

            var inputStream: InputStream = FileInputStream(urisArr)

            var secondRemoteFile = "/htdocs/rbks/code0 $num.jpg"
            inputStream = FileInputStream(urisArr)
            var outputStream = ftpClient.storeFileStream(secondRemoteFile)
            var bytesIn = ByteArray(33554432)
            var read = 0
            while (inputStream.read(bytesIn).also { read = it } != -1) {
                outputStream.write(bytesIn, 0, read)
            }
            inputStream.close()
            outputStream.close()
            var completed = ftpClient.completePendingCommand()
            if (completed) {
                infoos!!.text = ((infoos!!.text).toString().toInt() +1).toString()

                if((infoos!!.text).toString().toInt() == 6){
                    infoos!!.text  = "Finished uploading with sucess, waiting for callback"
                }
            }

        } catch (ex: IOException) {
            //infosTextvar.text="error ("+ ex.message+")"
            println("Error: " + ex.message)
            ex.printStackTrace()
        } finally {
            sleep(10_500)
        }
        try {
            if (ftpClient.isConnected) {
                ftpClient.logout()
                ftpClient.disconnect()
            }
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
    }

}