package com.eric.yang.mybatis;

import org.mybatis.generator.codegen.mybatis3.IntrospectedTableMyBatis3Impl;

import java.text.MessageFormat;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;

/**
 * 描述:
 *
 * @author eric
 * @create 2018-09-03 下午6:18
 */
public class EricMyBatis3Impl extends IntrospectedTableMyBatis3Impl {

    @Override
    protected String calculateMyBatis3XmlMapperFileName() {
        StringBuilder sb = new StringBuilder();
        if (stringHasValue(tableConfiguration.getMapperName())) {
            String mapperName = tableConfiguration.getMapperName();
            int ind = mapperName.lastIndexOf('.');
            if (ind != -1) {
                mapperName = mapperName.substring(ind + 1);
            }
            //支持mapperName = "{0}Dao" 等用法
            sb.append(MessageFormat.format(mapperName, fullyQualifiedTable.getDomainObjectName()));
            sb.append("Mapper.xml"); //$NON-NLS-1$
        } else {
            sb.append(fullyQualifiedTable.getDomainObjectName());
            sb.append("Mapper.xml"); //$NON-NLS-1$
        }
        return sb.toString();
    }

    @Override
    protected void calculateJavaClientAttributes() {
        if (context.getJavaClientGeneratorConfiguration() == null) {
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(calculateJavaClientImplementationPackage());
        sb.append('.');
        sb.append(fullyQualifiedTable.getDomainObjectName());
        sb.append("DAOImpl"); //$NON-NLS-1$
        setDAOImplementationType(sb.toString());

        sb.setLength(0);
        sb.append(calculateJavaClientInterfacePackage());
        sb.append('.');
        sb.append(fullyQualifiedTable.getDomainObjectName());
        sb.append("DAO"); //$NON-NLS-1$
        setDAOInterfaceType(sb.toString());

        sb.setLength(0);
        sb.append(calculateJavaClientInterfacePackage());
        sb.append('.');
        if (stringHasValue(tableConfiguration.getMapperName())) {
            //支持mapperName = "{0}Dao" 等用法
            sb.append(MessageFormat.format(tableConfiguration.getMapperName(), fullyQualifiedTable.getDomainObjectName()));
            sb.append("Mapper");
        } else {
            sb.append(fullyQualifiedTable.getDomainObjectName());
            sb.append("Mapper"); //$NON-NLS-1$
        }
        setMyBatis3JavaMapperType(sb.toString());

        sb.setLength(0);
        sb.append(calculateJavaClientInterfacePackage());
        sb.append('.');
        if (stringHasValue(tableConfiguration.getSqlProviderName())) {
            //支持mapperName = "{0}SqlProvider" 等用法
            sb.append(MessageFormat.format(tableConfiguration.getSqlProviderName(), fullyQualifiedTable.getDomainObjectName()));
            sb.append("Mapper");
        } else {
            sb.append(fullyQualifiedTable.getDomainObjectName());
            sb.append("SqlProvider"); //$NON-NLS-1$
        }
        setMyBatis3SqlProviderType(sb.toString());
    }

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        String mapperName = "WorkOrder.{0}";
        int ind = mapperName.lastIndexOf('.');
        if (ind != -1) {
            mapperName = mapperName.substring(ind + 1);
        }
        //支持mapperName = "{0}Dao" 等用法
        sb.append(MessageFormat.format(mapperName, "WorkOrder"));
        sb.append("Mapper.xml"); //$NON-NLS-1$
        System.out.println(sb.toString());
    }
}
