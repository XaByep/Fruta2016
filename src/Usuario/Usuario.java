package Usuario;

/**
 * Created by Yo on 25/10/2016.
 */
public class Usuario {
    private String nick;
    private String contraseña;

    public Usuario() {
    }

    public Usuario(String nick, String contraseña) {
        this.nick = nick;
        this.contraseña = contraseña;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nick='" + nick + '\'' +
                '}';
    }
}
