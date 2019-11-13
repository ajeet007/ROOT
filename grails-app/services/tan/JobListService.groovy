package tan

import com.jcraft.jsch.ChannelSftp
import com.jcraft.jsch.JSch
import com.jcraft.jsch.Session
import com.jcraft.jsch.SftpATTRS

class JobListService {


    def jobFile(File file,String fileName,String selectedDir) {
        try {
            SftpATTRS attrs=null;
            JSch jsch = new JSch();
            Session session = null;
            session = jsch.getSession("elemental", "192.168.1.200", 22);
            session.setPassword("elemental");
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            ChannelSftp channel = null;
            channel = (ChannelSftp) session.openChannel("sftp");
            channel.connect();
            try{
                attrs = channel.stat("/data/server/incoming/"+selectedDir+"/Copy/");
            }
            catch(Exception e){
                println("Dir not found");
            }

            if (attrs != null) {
                println("Directory exists IsDir="+attrs.isDir());
            } else {

                channel.mkdir("/data/server/incoming/"+selectedDir+"/Copy/");
            }
            channel.cd("/data/server/incoming/"+selectedDir+"/Copy/");
            channel.put(new FileInputStream(file),fileName);
            channel.disconnect();
            session.disconnect();
            return "success";
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
