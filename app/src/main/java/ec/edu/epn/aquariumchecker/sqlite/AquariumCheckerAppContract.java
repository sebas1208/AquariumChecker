package ec.edu.epn.aquariumchecker.sqlite;

import android.provider.BaseColumns;

/**
 * Created by natyd on 5/6/2016.
 */
public class AquariumCheckerAppContract {

    public static final String TEXT_TYPE = " TEXT";
    public static final String DATE = " DATE";
    public static final String INTEGER_TYPE = " INTEGER";
    public static final String DOUBLE_TYPE = " DOUBLE";
    public static final String COMMA_SEP = ",";


    //TABLA ACUARIO
    public static abstract class TablaAcuario implements BaseColumns {
        public static final String NOMBRE_TABLA = "ACUARIO";
        public static final String COLUMNA_NOMBRE = "NOMBRE";
        public static final String COLUMNA_TIPOAGUA = "TIPOAGUA";
        public static final String COLUMNA_FORMA = "FORMA";
        public static final String COLUMNA_ALTO = "ALTO";
        public static final String COLUMNA_ANCHO = "ANCHO";
        public static final String COLUMNA_PROFUNDIDAD_MEDIDAS = "PROFUNDIDAD_MEDIDAS";
        public static final String COLUMNA_DIAMETRO = "DIAMETRO";
        public static final String COLUMNA_PROFUNDIDAD_REDONDO = "PROFUNDIDAD_REDONDO";
        public static final String COLUMNA_VOLUMEN = "VOLUMEN";


        public static final String ELIMINA_TABLAS_ACUARIO = "DROP TABLE IF EXISTS " + TablaAcuario.NOMBRE_TABLA;
        public static final String CREA_TABLAS_ACUARIO = "CREATE TABLE " +
                TablaAcuario.NOMBRE_TABLA + " (" +
                TablaAcuario._ID + INTEGER_TYPE + " PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
                TablaAcuario.COLUMNA_NOMBRE + TEXT_TYPE + COMMA_SEP +
                TablaAcuario.COLUMNA_TIPOAGUA + TEXT_TYPE + COMMA_SEP +
                TablaAcuario.COLUMNA_FORMA + TEXT_TYPE + COMMA_SEP +
                TablaAcuario.COLUMNA_ALTO + DOUBLE_TYPE + COMMA_SEP +
                TablaAcuario.COLUMNA_ANCHO + DOUBLE_TYPE + COMMA_SEP +
                TablaAcuario.COLUMNA_PROFUNDIDAD_MEDIDAS + DOUBLE_TYPE + COMMA_SEP +
                TablaAcuario.COLUMNA_DIAMETRO + DOUBLE_TYPE + COMMA_SEP +
                TablaAcuario.COLUMNA_PROFUNDIDAD_REDONDO + DOUBLE_TYPE + COMMA_SEP +
                TablaAcuario.COLUMNA_VOLUMEN + DOUBLE_TYPE + " )";

        public static final String ELIMINA_TABLAS_PECES = "DROP TABLE IF EXISTS " + TablaPeces.NOMBRE_TABLA;
        public static final String CREA_TABLAS_TABLA_PECES = "CREATE TABLE " +
                TablaPeces.NOMBRE_TABLA + " (" +
                TablaPeces._ID + INTEGER_TYPE + " PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
                TablaPeces.ACUARIO_ID + INTEGER_TYPE + COMMA_SEP +
                TablaPeces.COLUMNA_FOTO + TEXT_TYPE + COMMA_SEP +
                TablaPeces.COLUMNA_DESCRIPCION + TEXT_TYPE + COMMA_SEP +
                "FOREIGN KEY (ACUARIO_ID) REFERENCES ACUARIO (_ID)" + " )";

        public static final String ELIMINA_TABLAS_PLANTAS = "DROP TABLE IF EXISTS " + TablaPlantas.NOMBRE_TABLA;
        public static final String CREA_TABLAS_PLANTAS = "CREATE TABLE " +
                TablaPlantas.NOMBRE_TABLA + " (" +
                TablaPlantas._ID + INTEGER_TYPE + " PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
                TablaPlantas.ACUARIO_ID + INTEGER_TYPE + COMMA_SEP +
                TablaPlantas.COLUMNA_FOTO + TEXT_TYPE + COMMA_SEP +
                TablaPlantas.COLUMNA_DESCRIPCION + TEXT_TYPE + COMMA_SEP +
                "FOREIGN KEY (ACUARIO_ID) REFERENCES ACUARIO (_ID)" + " )";

        public static final String ELIMINA_TABLAS_GALERIA = "DROP TABLE IF EXISTS " + TablaGaleria.NOMBRE_TABLA;
        public static final String CREA_TABLAS_GALERIA = "CREATE TABLE " +
                TablaGaleria.NOMBRE_TABLA + " (" +
                TablaGaleria._ID + INTEGER_TYPE + " PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
                TablaGaleria.ACUARIO_ID + INTEGER_TYPE + COMMA_SEP +
                TablaGaleria.COLUMNA_FECHA + DATE + COMMA_SEP +
                TablaGaleria.COLUMNA_OBSERVACIONES + TEXT_TYPE + COMMA_SEP +
                TablaGaleria.COLUMNA_FOTOS + TEXT_TYPE + COMMA_SEP +
                "FOREIGN KEY (ACUARIO_ID) REFERENCES ACUARIO (_ID)" + " )";

        public static final String ELIMINA_TABLAS_FOTO = "DROP TABLE IF EXISTS " + TablaFoto.NOMBRE_TABLA;
        public static final String CREA_TABLAS_FOTO = "CREATE TABLE " +
                TablaFoto.NOMBRE_TABLA + " (" +
                TablaFoto._ID + INTEGER_TYPE + " PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
                TablaFoto.GALERIA_ID + INTEGER_TYPE + COMMA_SEP +
                TablaFoto.COLUMNA_DESCRIPCION + TEXT_TYPE + COMMA_SEP +
                "FOREIGN KEY (GALERIA_ID) REFERENCES GALERIA (_ID)" + " )";

        public static final String ELIMINA_TABLAS_HISTORIAL = "DROP TABLE IF EXISTS " + TablaHistorial.NOMBRE_TABLA;
        public static final String CREA_TABLAS_HISTORIAL = "CREATE TABLE " +
                TablaHistorial.NOMBRE_TABLA + " (" +
                TablaHistorial._ID + INTEGER_TYPE + " PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
                TablaHistorial.ACUARIO_ID + INTEGER_TYPE + COMMA_SEP +
                TablaHistorial.COLUMNA_FECHA + DATE + COMMA_SEP +
                TablaHistorial.COLUMNA_PH + INTEGER_TYPE + COMMA_SEP +
                TablaHistorial.COLUMNA_KH + INTEGER_TYPE + COMMA_SEP +
                TablaHistorial.COLUMNA_GH + INTEGER_TYPE + COMMA_SEP +
                TablaHistorial.COLUMNA_CO2 + INTEGER_TYPE + COMMA_SEP +
                TablaHistorial.COLUMNA_OBSERVACIONES + TEXT_TYPE + COMMA_SEP +
                TablaHistorial.COLUMNA_ILUMINACION + TEXT_TYPE + COMMA_SEP +
                "FOREIGN KEY (ACUARIO_ID) REFERENCES ACUARIO (_ID)" + " )";

        public static final String ELIMINA_TABLAS_RECORDATORIO = "DROP TABLE IF EXISTS " + TablaRecordatorio.NOMBRE_TABLA;
        public static final String CREA_TABLAS_RECORDATORIO = "CREATE TABLE " +
                TablaRecordatorio.NOMBRE_TABLA + " (" +
                TablaRecordatorio._ID + INTEGER_TYPE + " PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
                TablaRecordatorio.ACUARIO_ID + INTEGER_TYPE + COMMA_SEP +
                TablaRecordatorio.COLUMNA_FECHA + DATE + COMMA_SEP +
                TablaRecordatorio.COLUMNA_HORA + INTEGER_TYPE + COMMA_SEP +
                TablaRecordatorio.COLUMNA_TIPORECORDATORIO + TEXT_TYPE + COMMA_SEP +
                "FOREIGN KEY (ACUARIO_ID) REFERENCES ACUARIO (_ID)" + " )";

    }


    //TABLA PECES
    public static abstract class TablaPeces implements BaseColumns {
        public static final String NOMBRE_TABLA = "PECES";
        public static final String COLUMNA_FOTO = "FOTO";
        public static final String COLUMNA_DESCRIPCION = "DESCRIPCION";
        public static final String ACUARIO_ID = "ACUARIO_ID";
    }


    //TABLA PLANTAS
    public static abstract class TablaPlantas implements BaseColumns {
        public static final String NOMBRE_TABLA = "PLANTAS";
        public static final String COLUMNA_FOTO = "NOMBRE";
        public static final String COLUMNA_DESCRIPCION = "DESCRIPCION";
        public static final String ACUARIO_ID = "ACUARIO_ID";
    }

    //TABLA GALERIA
    public static abstract class TablaGaleria implements BaseColumns {
        public static final String NOMBRE_TABLA = "GALERIA";
        public static final String COLUMNA_FECHA = "FECHA";
        public static final String COLUMNA_OBSERVACIONES = "OBSERVACIONES";
        public static final String COLUMNA_FOTOS = "FOTOS";


        public static final String ACUARIO_ID = "ACUARIO_ID";
    }

    //TABLA FOTO
    public static abstract class TablaFoto implements BaseColumns {
        public static final String NOMBRE_TABLA = "FOTO";
        public static final String COLUMNA_DESCRIPCION = "DESCRIPCION";
        public static final String GALERIA_ID = "GALERIA_ID";
    }


    //TABLA HISTORIAL
    public static abstract class TablaHistorial implements BaseColumns {
        public static final String NOMBRE_TABLA = "HISTORIAL";
        public static final String COLUMNA_FECHA = "FECHA";
        public static final String COLUMNA_PH = "PH";
        public static final String COLUMNA_KH = "KH";
        public static final String COLUMNA_GH = "GH";
        public static final String COLUMNA_CO2 = "CO2";
        public static final String COLUMNA_OBSERVACIONES = "OBSERVACIONES";
        public static final String COLUMNA_ILUMINACION = "ILUMINACION";

        public static final String ACUARIO_ID = "ACUARIO_ID";
    }

//TABLA RECORDATORIO

    public static abstract class TablaRecordatorio implements BaseColumns {
        public static final String NOMBRE_TABLA = "RECORDATORIO";
        public static final String COLUMNA_FECHA = "FECHA";
        public static final String COLUMNA_HORA = "HORA";
        public static final String COLUMNA_TIPORECORDATORIO = "TIPO";


        public static final String ACUARIO_ID = "ACUARIO_ID";
    }

}
