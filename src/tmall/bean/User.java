package tmall.bean;

/**
 * Created by Danger on 2017/10/12.
 */
public class User {
    private int id;
    private String name;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getAnonymousName(){
        if (null==name)
            return null;
        if (name.length()==2)
            return name.substring(0,1)+"*";
        char[] cs=name.toCharArray();
        for (int i=0;i<name.length()-1;i++)
            cs[i]='*';
        return new String(cs);
    }
}
