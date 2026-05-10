import java.util.HashMap;
import java.util.Map;

// === INTERFAZ BASE ===
interface IDatabase {
    void query(String sql);
}

// === SINGLETON ===
class Database implements IDatabase {
    private static Database instance;

    private Database() {
        System.out.println("Conectando a la base de datos...");
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    @Override
    public void query(String sql) {
        System.out.println("Ejecutando query: " + sql);
    }
}

// === DECORATOR BASE ===
abstract class DatabaseDecorator implements IDatabase {
    protected IDatabase database;

    public DatabaseDecorator(IDatabase database) {
        this.database = database;
    }

    @Override
    public void query(String sql) {
        database.query(sql);
    }
}

// === DECORATOR: Logging ===
class LoggingDatabase extends DatabaseDecorator {
    public LoggingDatabase(IDatabase database) {
        super(database);
    }

    @Override
    public void query(String sql) {
        System.out.println("[LOG] Query iniciada: " + sql);
        database.query(sql);
        System.out.println("[LOG] Query finalizada.");
    }
}

// === DECORATOR: Caché ===
class CachedDatabase extends DatabaseDecorator {
    private Map<String, String> cache = new HashMap<>();

    public CachedDatabase(IDatabase database) {
        super(database);
    }

    @Override
    public void query(String sql) {
        if (cache.containsKey(sql)) {
            System.out.println("[CACHE] Resultado desde caché para: " + sql);
        } else {
            database.query(sql);
            cache.put(sql, "resultado_guardado");
            System.out.println("[CACHE] Resultado guardado en caché.");
        }
    }
}

// === MAIN (clase pública, nombre del archivo) ===
public class Main {
    public static void main(String[] args) {
        IDatabase db = Database.getInstance();
        IDatabase loggedDb = new LoggingDatabase(db);
        IDatabase cachedLoggedDb = new CachedDatabase(loggedDb);

        cachedLoggedDb.query("SELECT * FROM usuarios");
        cachedLoggedDb.query("SELECT * FROM usuarios"); // Segunda vez: usa caché

        Database original = Database.getInstance();
        System.out.println("¿Sigue siendo el mismo objeto? " + (original == db));
    }
}