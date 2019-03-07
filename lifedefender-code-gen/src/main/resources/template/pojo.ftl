package ${pojoPackage};

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 * ${className}
 */
@Table(name = "${className}", schema = "")
@SuppressWarnings("serial")
public class ${classNameUpper} implements Serializable{
	<#list tableCarrays as tableCarray>
	<#if tableCarray.carrayComment != "">/**${tableCarray.carrayComment}*/</#if>
	private ${tableCarray.carrayType} ${tableCarray.carrayNameLower};
	
	</#list>
	public ${classNameUpper}() {
		super();
	}
	
	public ${classNameUpper}(${stringCarrayNames2}) {
		super();
		<#list tableCarrays as tableCarray>
		this.${tableCarray.carrayNameLower} = ${tableCarray.carrayNameLower}; 
		</#list>
	}
	
	<#list tableCarrays as tableCarray>
	@Column(name ="${tableCarray.carrayName}",nullable=${tableCarray.carrayIsNullablet}<#if tableCarray.carrayLength??>,length=${tableCarray.carrayLength}</#if>)
	public ${tableCarray.carrayType} get${tableCarray.carrayNameUpper}() {
		return ${tableCarray.carrayNameLower};
	}

	public void set${tableCarray.carrayNameUpper}(${tableCarray.carrayType} ${tableCarray.carrayNameLower}) {
		this.${tableCarray.carrayNameLower} = ${tableCarray.carrayNameLower};
	}
	</#list>
	
}
