 <#--

    Copyright MapStruct Authors.

    Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0

-->
 <#-- @ftlvariable name="" type="org.mapstruct.ap.internal.model.AnnotatedConstructor" -->
 <#if noArgumentConstructor??>
     <@includeModel object=noArgumentConstructor/>
</#if>

<#list annotations as annotation>
    <#nt><@includeModel object=annotation/>
</#list>
public ${name}(<#list arguments as argument><#if argument.annotations??><#list argument.annotations as annotation><@includeModel object=annotation/> </#list></#if><@includeModel object=argument.type/> ${argument.variableName}<#if argument_has_next>, </#if></#list>)<#if superClassConstructor??><#list superClassConstructor.thrownTypes as thrownType><#if thrownType_index == 0> throws </#if>${thrownType.name}<#if thrownType_has_next>, </#if></#list></#if> {
    <#if noArgumentConstructor?? && !noArgumentConstructor.fragments.empty>this();<#elseif superClassConstructor??>super( <#list superClassConstructor.arguments as argument>${argument.variableName}<#if argument_has_next>, </#if></#list> );</#if>
    <#list mapperReferences as mapperReference>
        this.${mapperReference.variableName} = ${mapperReference.variableName};
    </#list>
     <#list fragments as fragment>
         <#nt><@includeModel object=fragment/>
    </#list>
}