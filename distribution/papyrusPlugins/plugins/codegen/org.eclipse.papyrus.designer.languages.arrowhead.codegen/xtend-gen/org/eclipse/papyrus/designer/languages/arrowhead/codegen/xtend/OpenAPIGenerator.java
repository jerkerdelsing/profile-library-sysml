package org.eclipse.papyrus.designer.languages.arrowhead.codegen.xtend;

import java.util.HashSet;
import java.util.Set;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class OpenAPIGenerator {
  private static Set<org.eclipse.uml2.uml.Class> schemas = new HashSet<org.eclipse.uml2.uml.Class>();
  
  public static CharSequence generateOpenAPIyaml(final org.eclipse.uml2.uml.Class clazz) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("�schemas.clear�");
    _builder.newLine();
    _builder.append("openapi: 3.0.1");
    _builder.newLine();
    _builder.append("info: ");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("�OpenAPIInfo.readInfo(clazz, false)�");
    _builder.newLine();
    _builder.append("paths: ");
    _builder.newLine();
    _builder.append("�FOR attribute : clazz.attributes�");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("�IF attribute instanceof Port�");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("�IF !attribute.isConjugated�");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("�val classifier = attribute.type�");
    _builder.newLine();
    _builder.append("\t\t\t    ");
    _builder.append("�OpenAPIPaths.generatePath(classifier)�");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("�ENDIF�");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("�ENDIF�");
    _builder.newLine();
    _builder.append("�ENDFOR�");
    _builder.newLine();
    _builder.append("components: ");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("schemas: ");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("�OpenAPISchemas.generateSchema(schemas)�");
    _builder.newLine();
    return _builder;
  }
  
  public static CharSequence generateOpenAPIClientyaml(final org.eclipse.uml2.uml.Class clazz) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("�schemas.clear�");
    _builder.newLine();
    _builder.append("openapi: 3.0.1");
    _builder.newLine();
    _builder.append("info: ");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("�OpenAPIInfo.readInfo(clazz, true)�");
    _builder.newLine();
    _builder.append("paths: ");
    _builder.newLine();
    _builder.append("�FOR attribute : clazz.attributes�");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("�IF attribute instanceof Port�");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("�IF attribute.isConjugated�");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("�val classifier = attribute.type�");
    _builder.newLine();
    _builder.append("\t\t\t    ");
    _builder.append("�OpenAPIPaths.generatePath(classifier)�");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("�ENDIF�");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("�ENDIF�");
    _builder.newLine();
    _builder.append("�ENDFOR�");
    _builder.newLine();
    _builder.append("components: ");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("schemas: ");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("�OpenAPISchemas.generateSchema(schemas)�");
    _builder.newLine();
    return _builder;
  }
  
  public static Set<org.eclipse.uml2.uml.Class> getSchemas() {
    return OpenAPIGenerator.schemas;
  }
}
