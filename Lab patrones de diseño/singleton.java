public class Database {

    // Instancia única
    private static Database instance;

    // Constructor privado
    private Database() {
        System.out.println("Conectando a la base de datos...");
    }

    // Método estático singleton
    public static Database getInstance() {

        if (instance == null) {
            instance = new Database();
        }

        return instance;
    }

    // Lógica de negocio
    public void query(String sql) {
        System.out.println("Ejecutando query: " + sql);
    }

    // Método principal
    public static void main(String[] args) {

        Database foo = Database.getInstance();

        foo.query("SELECT * FROM usuarios");

        Database bar = Database.getInstance();

        bar.query("SELECT * FROM productos");

        // Verifica que son el mismo objeto
        System.out.println(foo == bar);
    }
}
