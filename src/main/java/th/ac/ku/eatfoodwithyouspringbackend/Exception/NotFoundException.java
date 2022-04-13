package th.ac.ku.eatfoodwithyouspringbackend.Exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(int id,String type) {
        super("Couldn't found " + type + " " + id);
    }
}
