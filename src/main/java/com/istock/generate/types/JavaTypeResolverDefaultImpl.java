package com.istock.generate.types;

import java.sql.Types;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;


/**
 * 重写JavaTypeResolverDefaultImpl类型解析方法
 * @author chenbing
 *
 */
public class JavaTypeResolverDefaultImpl  extends org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl {
	    
	    public JavaTypeResolverDefaultImpl() {
	        super();
	        typeMap.put(Types.SMALLINT, new JdbcTypeInformation("SMALLINT", //$NON-NLS-1$
	                new FullyQualifiedJavaType(Integer.class.getName())));
	    }
	    
	    
	    protected FullyQualifiedJavaType calculateBigDecimalReplacement(IntrospectedColumn column, FullyQualifiedJavaType defaultType) {
	        FullyQualifiedJavaType answer;
	        
	        if (column.getScale() > 0 || column.getLength() > 18 || forceBigDecimals) {
	            answer = defaultType;
	        } else if (column.getLength() > 9) {
	            answer = new FullyQualifiedJavaType(Long.class.getName());
	        } else if (column.getLength() > 4) {
	            answer = new FullyQualifiedJavaType(Integer.class.getName());
	        } else {
	            answer = new FullyQualifiedJavaType(Integer.class.getName());
	        }
	        
	        return answer;
	    }

}
