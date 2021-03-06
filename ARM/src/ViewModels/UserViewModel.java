package ViewModels;
import Models.ModelManager;
import Models.Salary;
import Models.User;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.internal.bulk.DeleteRequest;
import org.bson.Document;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class UserViewModel {
    /**
     * Kiểm tra xem managerCredential có đúng là manager thật hay không
     * Tìm và xóa bỏ ai có id truyền vào
     * Cho phép tự xóa
     * Nếu người bị xóa cũng là quản lý thì không cho xóa
     * Nếu id="Putin" thì tự xóa :)))
     * @param managerCredential Object của Manager
     * @param id Thằng sắp ra đảo
     * @return Kết quả cuộc chia ly
     */
    public CompletableFuture<Boolean> deleteUserAsync(User managerCredential, String id) {
        return null;
    }

    /**
     * Kiểm tra xem managerCredential có đúng là manager thật hay không
     * Trả về danh sách toàn bộ nhân viên các cấp ngoại trừ chính bản thân
     * @param managerCredential Object của Manager để kiểm tra xem có quyền hay không
     * @return Danh sách tất cả User
     */
    public CompletableFuture<ArrayList<User>> getUsersAsync(User managerCredential) {
        return null;
    }

    public CompletableFuture<Boolean> updateOneUser(User newUser) {
        return CompletableFuture.supplyAsync(new Supplier<Boolean>() {
            @Override
            public Boolean get() {
                try {
                    MongoDatabase db = ModelManager.getInstance().getDatabase();
                    MongoCollection<Document> userCollection = db.getCollection("User");
                    Document specificUser = userCollection.find(Filters.eq("ID", newUser.getID())).first();

                    if (specificUser == null) {
                        System.out.println("Can't find user");
                    } else {
                        updatePassword(newUser.getPassword(), userCollection, specificUser);
                        updateName(newUser.getName(), userCollection, specificUser);
                        updatePhoneNumber(newUser.getPhoneNumber(), userCollection, specificUser);
                        updateGender(newUser.getGender(), userCollection, specificUser);
                        updateDoB(newUser.getDoB(), userCollection, specificUser);
                        updateEmail(newUser.getEmail(), userCollection, specificUser);
                    }
                    return true;
                }
                catch(MongoException e) {
                    e.printStackTrace();
                }
                return false;
            }
        });
    }

    public void updatePassword(String newPassword, MongoCollection<Document> userCollection, Document document) throws MongoException {
        if(newPassword.isEmpty() || newPassword.isBlank()) {
            return;
        }
        else {
            String hashedPassword = org.apache.commons.codec.digest.DigestUtils.sha256Hex(newPassword);
            BasicDBObject newDocument = new BasicDBObject();
            newDocument.put("password", hashedPassword);
            BasicDBObject query = new BasicDBObject();
            query.put("ID", document.getString("ID"));
            BasicDBObject updateObject = new BasicDBObject();
            updateObject.put("$set", newDocument);

            userCollection.updateOne(query, updateObject);
        }
    }
    public void updateName(String newName, MongoCollection<Document> userCollection, Document document) throws MongoException {
        if(newName.isEmpty()) {
            return;
        }
        else {
            BasicDBObject newDocument = new BasicDBObject();
            newDocument.put("name", newName);
            BasicDBObject query = new BasicDBObject();
            query.put("ID", document.getString("ID"));
            BasicDBObject updateObject = new BasicDBObject();
            updateObject.put("$set", newDocument);

            userCollection.updateOne(query, updateObject);
        }
    }

    public void updatePhoneNumber(String newPhoneNumber, MongoCollection<Document> userCollection, Document document) throws MongoException {
        if(newPhoneNumber.isEmpty()) {
            return;
        }
        else {
            BasicDBObject newDocument = new BasicDBObject();
            newDocument.put("phoneNumber", newPhoneNumber);
            BasicDBObject query = new BasicDBObject();
            query.put("ID", document.getString("ID"));
            BasicDBObject updateObject = new BasicDBObject();
            updateObject.put("$set", newDocument);

            userCollection.updateOne(query, updateObject);
        }
    }

    public void updateDoB(String newDoB, MongoCollection<Document> userCollection, Document document) throws MongoException {
        if(newDoB.isEmpty()) {
            return;
        }
        else {
            BasicDBObject newDocument = new BasicDBObject();
            newDocument.put("DoB", newDoB);
            BasicDBObject query = new BasicDBObject();
            query.put("ID", document.getString("ID"));
            BasicDBObject updateObject = new BasicDBObject();
            updateObject.put("$set", newDocument);

            userCollection.updateOne(query, updateObject);
        }
    }

    public void updateGender(String newGender, MongoCollection<Document> userCollection, Document document) throws MongoException {
        if(newGender.isEmpty()) {
            return;
        }
        else {
            BasicDBObject newDocument = new BasicDBObject();
            newDocument.put("gender", newGender);
            BasicDBObject query = new BasicDBObject();
            query.put("ID", document.getString("ID"));
            BasicDBObject updateObject = new BasicDBObject();
            updateObject.put("$set", newDocument);

            userCollection.updateOne(query, updateObject);
        }
    }

    public void updateEmail(String newEmail, MongoCollection<Document> userCollection, Document document) throws MongoException {
        if(newEmail.isEmpty()) {
            return;
        }
        else {
            BasicDBObject newDocument = new BasicDBObject();
            newDocument.put("email", newEmail);
            BasicDBObject query = new BasicDBObject();
            query.put("ID", document.getString("ID"));
            BasicDBObject updateObject = new BasicDBObject();
            updateObject.put("$set", newDocument);

            userCollection.updateOne(query, updateObject);
        }
    }

    public CompletableFuture<Boolean> deleteUserAsync(String userID) {
        return CompletableFuture.supplyAsync(new Supplier<Boolean>() {
            @Override
            public Boolean get() {
                try {
                    MongoDatabase db = ModelManager.getInstance().getDatabase();
                    MongoCollection<Document> userCollection = db.getCollection("User");
//                    Document specificUser = userCollection.find(Filters.eq("ID", userID)).first();
//                    if (specificUser.isEmpty()) {
//                        System.out.println("User not exist");
//                        return false;
//                    }
                    BasicDBObject query = new BasicDBObject();
                    query.put("ID", userID);
                    DeleteResult res = userCollection.deleteMany(query);
                    System.out.println("Affected documents: " + res.getDeletedCount());
                    return true;
                } catch (MongoException e) {
                    e.printStackTrace();
                }
                return false;
            }
        });
    }

    public CompletableFuture<ArrayList<User>> getUsersAsync(String onUsingID){
        return CompletableFuture.supplyAsync(new Supplier<ArrayList<User>>() {
            @Override
            public ArrayList<User> get() {
                try{
                    MongoDatabase db = ModelManager.getInstance().getDatabase();
                    MongoCollection<Document> userCollection = db.getCollection("User");

                    ArrayList<User> usersList = new ArrayList<>();
                    for(Document u: userCollection.find()){
                        ArrayList<Salary> s = (ArrayList<Salary>)u.get("salary");

                        User user = new User(
                            u.getString("ID"),
                            u.getString("username"),
                            u.getString("password"),
                            u.getString("name"),
                            u.getString("phoneNumber"),
                            u.getString("DoB"),
                            u.getString("gender"),
                            u.getString("email"),
                            u.getString("citizenID"),
                            s
                        );

                        if (onUsingID.compareTo(user.getID()) != 0)
                            usersList.add(user);
                    }

                    return usersList;
                } catch (Exception exc){
                    exc.printStackTrace();
                    return null;
                }
            }
        });
    }
}
