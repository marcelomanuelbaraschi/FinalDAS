package activerecord;

import activerecord.annotations.Column;
import activerecord.annotations.Entity;
import activerecord.exceptions.NoEntityException;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;


public abstract class ActiveRecord<Record> extends ConnectionDB<Record> {
    protected String tableName;
    protected Stream<String> columns;
    protected String columnNames;
    protected Stream<String> attributes;
    protected String attributeNames;
    protected Long identifier;
    public ActiveRecord(Long identifier, Class<Record> clazz) throws NoEntityException {
        super(clazz);
        DatasourceConfig datasourceConfig = ActiveRecord.getDatasource();
        this.setDatasource(datasourceConfig);
        this.identifier = identifier;
        tableName = getTableName(clazz);
        columns = getColumns(clazz);
        columnNames = columns.collect(joining(","));
        attributes = getAttributeNames(clazz);
        attributeNames = attributes.collect(joining(","));
    }

    // API
    public void update(Record bean, String... columnNames) throws SQLException {
        String columnsToSet = Stream.of(columnNames).map(cn -> cn + " = ?").collect(joining(","));
        String update = String.format("UPDATE %s SET %s WHERE id = ?", tableName, columnsToSet);
        this.connect();
        this.setPrearedStatement(update);
        this.setParameters(bean, columnNames);
        this.setParameter(Stream.of(columnNames).toArray().length + 1, identifier);
        this.executeUpdate();
        this.disconnect();

        // UPDATE cadena SET tecnologia = ? WHERE id = ?
    }
    public Optional<Record> select() throws SQLException {
        String select = String.format("SELECT %s FROM %s WHERE id = ?", columnNames, tableName);
        this.connect();
        this.setPrearedStatement(select);
        this.setParameter(1, identifier);
        Optional<Record> result = this.executeQuery().stream().findFirst();
        this.disconnect();
        return result;

        // SELECT id,nombre,tecnologia,url FROM cadena WHERE id = 1
    }
    public void delete() throws SQLException {
        String delete = String.format("DELETE FROM %s WHERE id = ?", tableName);
        this.connect();
        this.setPrearedStatement(delete);
        this.setParameter(1, identifier);
        this.executeUpdate();
        this.disconnect();
    }
    public static <T> void insert(T bean) throws SQLException, NoEntityException {
        String columnsToInsert = getColumns(bean.getClass()).filter(c -> !c.equals("id")).collect(joining(","));
        String questionMarks = getColumns(bean.getClass()).filter(c -> !c.equals("id")).map(c -> "?").collect(joining(","));
        String insert = String.format("INSERT INTO %s (%s) VALUES (%s)",
                getTableName(bean.getClass()), columnsToInsert, questionMarks);
        List<String> attrs = getAttributeNames(bean.getClass()).filter(c -> !c.equals("id")).collect(toList());
        try {
            DatasourceConfig datasourceConfig = getDatasource();
            Class.forName(datasourceConfig.getDriver()).newInstance();
            try(final Connection connection =  DriverManager.getConnection(datasourceConfig.getUrl(),
                    datasourceConfig.getUsername(), datasourceConfig.getPassword())) {

                connection.setAutoCommit(true);
                final PreparedStatement st = connection.prepareStatement(insert);
                setBeanParameters(st, bean, attrs);
                st.executeUpdate();
                connection.commit();
                connection.setAutoCommit(false);
            }
        } catch (final InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static DatasourceConfig getDatasource() {
        DatasourceConfig datasourceConfig = new DatasourceConfig();
        datasourceConfig.setDriver("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        datasourceConfig.setUrl("jdbc:sqlserver://192.168.207:1439;databaseName=db_indec_service");
        datasourceConfig.setUsername("sa");
        datasourceConfig.setPassword("Das12345");
        return datasourceConfig;
    }
    private static <T> String getTableName(Class<T> claxx) throws NoEntityException {
        if (!claxx.isAnnotationPresent(Entity.class))
            throw new NoEntityException();

        Entity entity = claxx.getAnnotation(Entity.class);
        return entity.name();
    }
    private static <T> Stream<String> getColumns(Class<T> claxx) throws NoEntityException {
        if (!claxx.isAnnotationPresent(Entity.class))
            throw new NoEntityException();

        final Field[] attributes = claxx.getDeclaredFields(); // get all the attributes of Class clazz

        // iterating over clazz attributes to check
        return Stream.of(attributes)
                .map(attribute -> {
                    // if any attribute has 'Column' annotation with matching 'name' value
                    if (attribute.isAnnotationPresent(Column.class)) {
                        final Column column = attribute.getAnnotation(Column.class); // get @Column for field
                        return column.name();
                    }
                    return "";
                })
                .filter(s -> !s.isEmpty());
    }
    private static <T> Stream<String> getAttributeNames(Class<T> claxx) throws NoEntityException {
        if (!claxx.isAnnotationPresent(Entity.class))
            throw new NoEntityException();

        final Field[] attributes = claxx.getDeclaredFields(); // get all the attributes of Class clazz
        return Stream.of(attributes).map(attribute -> attribute.getName());
    }
    private static <T> void setBeanParameters(final PreparedStatement stm,
                                              final T bean,
                                              final List<String> attrs) throws SQLException, NoEntityException {
        int i = 1;
        for (final String attributeName : attrs) {
            try {
                final Field field = bean.getClass().getDeclaredField(attributeName);
                field.setAccessible(true); // in case the field is private
                setPreparedStatementParameter(stm, i, field.get(bean));
            } catch (final NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
            i++;
        }
    }
}

// BEAN

class Runner {
    public static void main(String[] args) throws SQLException, NoEntityException {
        /*ServiceConfigRecord serviceConfigRecord = new ServiceConfigRecord(4L, ServiceConfig.class);
        Optional<ServiceConfig> select1 = serviceConfigRecord.select();
        System.out.println(select1.get());
        // serviceConfigRecord.delete();
        Optional<ServiceConfig> select2 = serviceConfigRecord.select();
        System.out.println(select2.get());
        ServiceConfig config = new ServiceConfig();
        config.setNombreCadena("Hola");
        config.setTecnologia("SOAP");
        config.setUrl("URL");
        ServiceConfigRecord.insert(config);
        Optional<ServiceConfig> select3 = serviceConfigRecord.select();
        System.out.println(select3.get());
        ServiceConfig newConfig = new ServiceConfig();
        newConfig.setTecnologia("RET");
        serviceConfigRecord.update(newConfig, "tecnologia");
        Optional<ServiceConfig> select4 = serviceConfigRecord.select();
        System.out.println(select4.get());*/
        /*ServiceConfigRecord serviceConfigRecord = new ServiceConfigRecord(4L, ServiceConfig.class);*/
    }
}