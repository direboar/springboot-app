package jp.direboar.spring.boot.app.testutil;

import java.util.Map;

import javax.sql.DataSource;

import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.DefaultPrepAndExpectedTestCase;
import org.dbunit.IDatabaseTester;
import org.dbunit.PrepAndExpectedTestCase;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.ext.h2.H2DataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.dbunit.util.fileloader.DataFileLoader;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

@Configuration
// DBUnit5のSpring連携のサンプルをほぼそのまま採用。
// @see http://dbunit.sourceforge.net/testcases/PrepAndExpectedTestCase.html
public class DbUnitConfiguration {
    /**
     * Extend DefaultPrepAndExpectedTestCase to customize DatabaseConfig.
     */
    private class ExtendedPrepAndExpectedTestCase extends DefaultPrepAndExpectedTestCase {
        public ExtendedPrepAndExpectedTestCase(final DataFileLoader dataFileLoader,
            final IDatabaseTester databaseTester) {
            super(dataFileLoader, databaseTester);
        }

        @Override
        protected void setUpDatabaseConfig(final DatabaseConfig config) {
            // set properties as needed
            config.setProperty(DatabaseConfig.FEATURE_BATCHED_STATEMENTS, true);

            // set the specific IDataTypeFactory if needed
            config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new H2DataTypeFactory());
        }
    }

    /**
     * Create dbUnit {@link PrepAndExpectedTestCase} for running dbUnit database tests.
     *
     * @param dataFileLoader The {@link DataFileLoader} used to load the test's specified data
     *        files.
     * @param databaseTester The {@link IDatabaseTester} used to run the tests against the database.
     * @return Configured dbUnit {@link PrepAndExpectedTestCase} for running dbUnit database tests.
     */
    @Bean
    public PrepAndExpectedTestCase prepAndExpectedTestCase(final DataFileLoader dataFileLoader,
        final IDatabaseTester databaseTester) {
        return new ExtendedPrepAndExpectedTestCase(dataFileLoader, databaseTester);
    }

    /**
     * Create dbUnit {@link DataFileLoader} for loading the test's dbUnit data files.
     *
     * @param ddr Your local class containing the replacement definitions.
     * @return Configured dbUnit {@link DataFileLoader} for loading the test's dbUnit data files.
     */
    @Bean
    public DataFileLoader dataFileLoader(final DbUnitDataReplacement ddr) {
        final Map<String, Object> replacementObjects = ddr.getReplacementObjects();
        final Map<String, Object> replacementSubstrings = ddr.getReplacementSubstrings();
        return new FlatXmlDataFileLoader(replacementObjects, replacementSubstrings);
    }

    /**
     * Create dbUnit {@link IDatabaseTester}.
     * 
     * @param dataSource The {@link DataSource} for the dbUnit test to use.
     * @return Configured dbUnit {@link IDatabaseTester}.
     */
    @Bean
    public IDatabaseTester databaseTester(final DataSource dataSource) {
        final DataSource dataSourceProxy = new TransactionAwareDataSourceProxy(dataSource);

        final IDatabaseTester databaseTester = new DataSourceDatabaseTester(dataSourceProxy);
        databaseTester.setTearDownOperation(DatabaseOperation.DELETE_ALL);

        return databaseTester;
    }

    @Bean
    public DbUnitDataReplacement dbUnitDataReplacement() {
        return new DbUnitDataReplacement();
    }
}
