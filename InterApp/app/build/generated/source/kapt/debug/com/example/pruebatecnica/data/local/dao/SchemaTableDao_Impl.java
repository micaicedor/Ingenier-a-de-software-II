package com.example.pruebatecnica.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.pruebatecnica.data.local.entity.SchemaTableEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class SchemaTableDao_Impl implements SchemaTableDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<SchemaTableEntity> __insertionAdapterOfSchemaTableEntity;

  private final SharedSQLiteStatement __preparedStmtOfClearTables;

  public SchemaTableDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSchemaTableEntity = new EntityInsertionAdapter<SchemaTableEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `tablas_sincronizadas` (`id`,`nombreTabla`,`pk`,`batchSize`,`fechaActualizacionSincro`,`descripcion`,`rawJson`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final SchemaTableEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getNombreTabla() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getNombreTabla());
        }
        if (entity.getPk() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getPk());
        }
        statement.bindLong(4, entity.getBatchSize());
        if (entity.getFechaActualizacionSincro() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getFechaActualizacionSincro());
        }
        if (entity.getDescripcion() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getDescripcion());
        }
        if (entity.getRawJson() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getRawJson());
        }
      }
    };
    this.__preparedStmtOfClearTables = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM tablas_sincronizadas";
        return _query;
      }
    };
  }

  @Override
  public Object insertTables(final List<SchemaTableEntity> tables,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfSchemaTableEntity.insert(tables);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object clearTables(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearTables.acquire();
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfClearTables.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getTables(final Continuation<? super List<SchemaTableEntity>> $completion) {
    final String _sql = "SELECT * FROM tablas_sincronizadas ORDER BY nombreTabla ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<SchemaTableEntity>>() {
      @Override
      @NonNull
      public List<SchemaTableEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNombreTabla = CursorUtil.getColumnIndexOrThrow(_cursor, "nombreTabla");
          final int _cursorIndexOfPk = CursorUtil.getColumnIndexOrThrow(_cursor, "pk");
          final int _cursorIndexOfBatchSize = CursorUtil.getColumnIndexOrThrow(_cursor, "batchSize");
          final int _cursorIndexOfFechaActualizacionSincro = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaActualizacionSincro");
          final int _cursorIndexOfDescripcion = CursorUtil.getColumnIndexOrThrow(_cursor, "descripcion");
          final int _cursorIndexOfRawJson = CursorUtil.getColumnIndexOrThrow(_cursor, "rawJson");
          final List<SchemaTableEntity> _result = new ArrayList<SchemaTableEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final SchemaTableEntity _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpNombreTabla;
            if (_cursor.isNull(_cursorIndexOfNombreTabla)) {
              _tmpNombreTabla = null;
            } else {
              _tmpNombreTabla = _cursor.getString(_cursorIndexOfNombreTabla);
            }
            final String _tmpPk;
            if (_cursor.isNull(_cursorIndexOfPk)) {
              _tmpPk = null;
            } else {
              _tmpPk = _cursor.getString(_cursorIndexOfPk);
            }
            final int _tmpBatchSize;
            _tmpBatchSize = _cursor.getInt(_cursorIndexOfBatchSize);
            final String _tmpFechaActualizacionSincro;
            if (_cursor.isNull(_cursorIndexOfFechaActualizacionSincro)) {
              _tmpFechaActualizacionSincro = null;
            } else {
              _tmpFechaActualizacionSincro = _cursor.getString(_cursorIndexOfFechaActualizacionSincro);
            }
            final String _tmpDescripcion;
            if (_cursor.isNull(_cursorIndexOfDescripcion)) {
              _tmpDescripcion = null;
            } else {
              _tmpDescripcion = _cursor.getString(_cursorIndexOfDescripcion);
            }
            final String _tmpRawJson;
            if (_cursor.isNull(_cursorIndexOfRawJson)) {
              _tmpRawJson = null;
            } else {
              _tmpRawJson = _cursor.getString(_cursorIndexOfRawJson);
            }
            _item = new SchemaTableEntity(_tmpId,_tmpNombreTabla,_tmpPk,_tmpBatchSize,_tmpFechaActualizacionSincro,_tmpDescripcion,_tmpRawJson);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
