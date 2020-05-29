package com.p4.bank.utilnew;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;
import com.alibaba.druid.stat.TableStat;
import com.alibaba.druid.util.JdbcConstants;
import org.springframework.util.ObjectUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author lzx
 * 解析表名
 */
public class GetTableNames {

   public static Set<String> getTableNames(String sql){
       HashSet<String> strings = new HashSet<String>();
       String dbType = JdbcConstants.MYSQL;
       List<SQLStatement> stmtList = SQLUtils.parseStatements(sql, dbType);
       for (SQLStatement sqlStatement : stmtList) {
           MySqlSchemaStatVisitor visitor = new MySqlSchemaStatVisitor();
           sqlStatement.accept(visitor);
           if(ObjectUtils.isEmpty(visitor.getTables())){
               continue;
           }
           Set<TableStat.Name> names = visitor.getTables().keySet();
           for (TableStat.Name name : names) {
               strings.add(name.getName());
           }
       }
       return strings;
   }

}
