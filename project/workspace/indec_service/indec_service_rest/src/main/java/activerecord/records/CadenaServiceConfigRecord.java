package activerecord.records;



import activerecord.ActiveRecord;
import activerecord.exceptions.NoEntityException;
import beans.CadenaServiceConfig;

public class CadenaServiceConfigRecord extends ActiveRecord<CadenaServiceConfig> {
    public CadenaServiceConfigRecord(Long identifier, Class<CadenaServiceConfig> clazz) throws NoEntityException {
        super(identifier, clazz);
    }
}
