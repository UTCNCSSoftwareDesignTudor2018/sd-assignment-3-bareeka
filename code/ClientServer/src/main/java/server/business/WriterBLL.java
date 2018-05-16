package server.business;

import server.persistence.entity.Writer;
import server.persistence.repository.WriterRepository;

public class WriterBLL {

    private static WriterRepository wr = new WriterRepository();
    private static WriterBLL singleInstance = new WriterBLL();

    private WriterBLL(){
    }

    public static WriterBLL getSingleInstance(){
        return singleInstance;
    }

    public synchronized void updateWriter(String name, Writer w){
        wr.updateWriter(name, w);
    }

    public synchronized Writer findByUsername(String username){
        return wr.findByName(username);
    }
}
