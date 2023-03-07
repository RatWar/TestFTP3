package com.anvarov.testftp3

import android.widget.TextView
import org.apache.commons.net.ftp.FTP
import org.apache.commons.net.ftp.FTPClient
import org.apache.commons.net.ftp.FTPClientConfig
import org.apache.commons.net.ftp.FTPReply
import java.io.File
import java.io.IOException
import kotlin.system.exitProcess


class FTPthread():Thread(){
    //767|1652&365|748
    var urisArr : File?=null
    var num :Int?=null
    var infoos : TextView?=null

    override fun run() {
        val server = "ftp1.oas-orb.ru"
        val user = "00000000118334"
        val pass = "T08FZVqk"
        val ftpClient = FTPClient()
        try {
            ftpClient.connect(server, FTP.DEFAULT_PORT)
            ftpClient.login(user, pass)
            val reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                System.err.println("FTP сервер не принимает подключение.");
                exitProcess(1);
            }
            ftpClient.enterLocalPassiveMode()
            ftpClient.setFileType(FTP.NON_PRINT_TEXT_FORMAT)

//            var inputStream: InputStream = FileInputStream(urisArr)
//
//            var secondRemoteFile = "/htdocs/rbks/code0 $num.jpg"
//            inputStream = FileInputStream(urisArr)
//            var outputStream = ftpClient.storeFileStream(secondRemoteFile)
//            var bytesIn = ByteArray(33554432)
//            var read = 0
//            while (inputStream.read(bytesIn).also { read = it } != -1) {
//                outputStream.write(bytesIn, 0, read)
//            }
//            inputStream.close()
//            outputStream.close()
//            var completed = ftpClient.completePendingCommand()
//            if (completed) {
//                infoos!!.text = ((infoos!!.text).toString().toInt() +1).toString()
//
//                if((infoos!!.text).toString().toInt() == 6){
//                    infoos!!.text  = "Finished uploading with sucess, waiting for callback"
//                }
//            }

        } catch (ex: IOException) {
            //infosTextvar.text="error ("+ ex.message+")"
            println("Error: " + ex.message)
            ex.printStackTrace()
        } finally {
            sleep(500)
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