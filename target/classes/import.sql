

/*==============================================================*/
/* Table: ACUARIO                                               */
/*==============================================================*/
create table ACUARIO (
   ID_ACUARIO           SERIAL                 not null,
   NOMBRE               VARCHAR(100)            not null,
   TIPOAGUA             VARCHAR(50)            null,
   FORMA                VARCHAR(50)            null,
   ALTO                 FLOAT8               null,
   ANCHO                FLOAT8               null,
   PROF_MEDIDAS         FLOAT8               null,
   DIAMETRO             FLOAT8               null,
   PROF_REDONDO         FLOAT8               null,
   VOLUMEN              FLOAT8               null,
   constraint PK_ACUARIO primary key (ID_ACUARIO)
);

/*==============================================================*/
/* Index: ACUARIO_PK                                            */
/*==============================================================*/
create unique index ACUARIO_PK on ACUARIO (
ID_ACUARIO
);

/*==============================================================*/
/* Table: FOTO                                                  */
/*==============================================================*/
create table FOTO (
   ID_FOTO              SERIAL                 not null,
   ID_GALERIA           SERIAL                 not null,
   DESCRIPCION          VARCHAR(100)            null,
   PATH                 VARCHAR(500)            null,
   constraint PK_FOTO primary key (ID_FOTO)
);

/*==============================================================*/
/* Index: FOTO_PK                                               */
/*==============================================================*/
create unique index FOTO_PK on FOTO (
ID_FOTO
);

/*==============================================================*/
/* Index: RELATIONSHIP_7_FK                                     */
/*==============================================================*/
create  index RELATIONSHIP_7_FK on FOTO (
ID_GALERIA
);

/*==============================================================*/
/* Table: GALERIA                                               */
/*==============================================================*/
create table GALERIA (
   ID_GALERIA           SERIAL                 not null,
   ID_ACUARIO           SERIAL                 not null,
   FECHA                DATE                 null,
   OBSERVACIONES        VARCHAR(100)          null,
   FOTOS                VARCHAR(256)            null,
   constraint PK_GALERIA primary key (ID_GALERIA)
);

/*==============================================================*/
/* Index: GALERIA_PK                                            */
/*==============================================================*/
create unique index GALERIA_PK on GALERIA (
ID_GALERIA
);

/*==============================================================*/
/* Index: RELATIONSHIP_8_FK                                     */
/*==============================================================*/
create  index RELATIONSHIP_8_FK on GALERIA (
ID_ACUARIO
);

/*==============================================================*/
/* Table: HISTORIAL                                             */
/*==============================================================*/
create table HISTORIAL (
   ID_HISTORIAL         SERIAL                 not null,
   ID_ACUARIO           SERIAL                 not null,
   FECHA                DATE                 null,
   PH                   FLOAT8               null,
   KH                   FLOAT8               null,
   GH                   FLOAT8               null,
   CO2                  FLOAT8               null,
   OBSERVACIONES        VARCHAR(100)           null,
   ILUMINACION          VARCHAR(50)            null,
   constraint PK_HISTORIAL primary key (ID_HISTORIAL)
);

/*==============================================================*/
/* Index: HISTORIAL_PK                                          */
/*==============================================================*/
create unique index HISTORIAL_PK on HISTORIAL (
ID_HISTORIAL
);

/*==============================================================*/
/* Index: RELATIONSHIP_1_FK                                     */
/*==============================================================*/
create  index RELATIONSHIP_1_FK on HISTORIAL (
ID_ACUARIO
);

/*==============================================================*/
/* Table: PECES                                                 */
/*==============================================================*/
create table PECES (
   ID_PEZ               SERIAL                 not null,
   ID_ACUARIO           SERIAL                 not null,
   NOMBRE               VARCHAR(100)            not null,
   DESCRIPCION          VARCHAR(100)            not null,
   CANTIDAD             INT4                 not null,
   FOTO                 VARCHAR(500)            not null,
   constraint PK_PECES primary key (ID_PEZ)
);

/*==============================================================*/
/* Index: PECES_PK                                              */
/*==============================================================*/
create unique index PECES_PK on PECES (
ID_PEZ,
NOMBRE,
DESCRIPCION,
CANTIDAD,
FOTO
);

/*==============================================================*/
/* Index: RELATIONSHIP_6_FK                                     */
/*==============================================================*/
create  index RELATIONSHIP_6_FK on PECES (
ID_ACUARIO
);

/*==============================================================*/
/* Table: PLANTAS                                               */
/*==============================================================*/
create table PLANTAS (
   ID_PLANTA            SERIAL                 not null,
   ID_ACUARIO           SERIAL                 not null,
   NOMBRE               VARCHAR(100)            not null,
   DESCRIPCION          VARCHAR(100)            not null,
   CANTIDAD             INT4                 not null,
   FOTO                 VARCHAR(500)            not null,
   constraint PK_PLANTAS primary key (ID_PLANTA)
);

/*==============================================================*/
/* Index: PLANTAS_PK                                            */
/*==============================================================*/
create unique index PLANTAS_PK on PLANTAS (
ID_PLANTA
);

/*==============================================================*/
/* Index: RELATIONSHIP_5_FK                                     */
/*==============================================================*/
create  index RELATIONSHIP_5_FK on PLANTAS (
ID_ACUARIO
);

/*==============================================================*/
/* Table: RECORDATORIO                                          */
/*==============================================================*/
create table RECORDATORIO (
   ID_RECORDATORIO      SERIAL                 not null,
   ID_ACUARIO           SERIAL                 not null,
   FECHA                DATE                 null,
   HORA                 DATE                 null,
   TIPO_RECORDATORIO    VARCHAR(100)            null,
   constraint PK_RECORDATORIO primary key (ID_RECORDATORIO)
);

/*==============================================================*/
/* Index: RECORDATORIO_PK                                       */
/*==============================================================*/
create unique index RECORDATORIO_PK on RECORDATORIO (
ID_RECORDATORIO
);

/*==============================================================*/
/* Index: RELATIONSHIP_2_FK                                     */
/*==============================================================*/
create  index RELATIONSHIP_2_FK on RECORDATORIO (
ID_ACUARIO
);

alter table FOTO
   add constraint FK_FOTO_RELATIONS_GALERIA foreign key (ID_GALERIA)
      references GALERIA (ID_GALERIA)
      on delete restrict on update restrict;

alter table GALERIA
   add constraint FK_GALERIA_RELATIONS_ACUARIO foreign key (ID_ACUARIO)
      references ACUARIO (ID_ACUARIO)
      on delete restrict on update restrict;

alter table HISTORIAL
   add constraint FK_HISTORIA_RELATIONS_ACUARIO foreign key (ID_ACUARIO)
      references ACUARIO (ID_ACUARIO)
      on delete restrict on update restrict;

alter table PECES
   add constraint FK_PECES_RELATIONS_ACUARIO foreign key (ID_ACUARIO)
      references ACUARIO (ID_ACUARIO)
      on delete restrict on update restrict;

alter table PLANTAS
   add constraint FK_PLANTAS_RELATIONS_ACUARIO foreign key (ID_ACUARIO)
      references ACUARIO (ID_ACUARIO)
      on delete restrict on update restrict;

alter table RECORDATORIO
   add constraint FK_RECORDAT_RELATIONS_ACUARIO foreign key (ID_ACUARIO)
      references ACUARIO (ID_ACUARIO)
      on delete restrict on update restrict;
