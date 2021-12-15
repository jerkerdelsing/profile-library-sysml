package org.eclipse.papyrus.designer.languages.arrowhead.codegen.xtend;

import com.google.common.base.Objects;
import java.util.Set;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class OpenAPISchemas {
  public static CharSequence generateSchema(final Set<org.eclipse.uml2.uml.Class> schemas) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("�FOR schema : schemas�");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("�schema.name�:");
    _builder.newLine();
    _builder.append("\t    ");
    _builder.append("type: object");
    _builder.newLine();
    _builder.append("\t    ");
    _builder.append("�IF schema.allAttributes() !== null�");
    _builder.newLine();
    _builder.append("\t    \t");
    _builder.append("�IF schema.allAttributes().length > 0�");
    _builder.newLine();
    _builder.append("\t    \t\t");
    _builder.append("properties: ");
    _builder.newLine();
    _builder.append("\t    \t\t    ");
    _builder.append("�FOR att : schema.allAttributes()�");
    _builder.newLine();
    _builder.append("\t    \t\t    \t");
    _builder.append("�IF att.type !== null�");
    _builder.newLine();
    _builder.append("\t    \t\t    \t\t");
    _builder.append("�att.name�:");
    _builder.newLine();
    _builder.append("\t    \t\t    \t\t    ");
    _builder.append("�IF schemas.contains(att.type)�");
    _builder.newLine();
    _builder.append("\t    \t\t    \t\t    \t");
    _builder.append("�IF att.unique�");
    _builder.newLine();
    _builder.append("\t    \t\t    \t\t    \t\t");
    _builder.append("type: object");
    _builder.newLine();
    _builder.append("\t    \t\t    \t\t    \t");
    _builder.append("�ELSE�");
    _builder.newLine();
    _builder.append("\t    \t\t    \t\t    \t\t");
    _builder.append("type: array");
    _builder.newLine();
    _builder.append("\t    \t\t    \t\t    \t");
    _builder.append("�ENDIF�");
    _builder.newLine();
    _builder.append("\t    \t\t    \t\t    \t");
    _builder.append("items: ");
    _builder.newLine();
    _builder.append("\t    \t\t    \t\t    \t    ");
    _builder.append("$ref: \"#/components/schemas/�att.type.name�\"");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("�ELSE�");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("�transferPrimitiveTypes(att.type.name.toLowerCase)�");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("�ENDIF�");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("�ENDIF�");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("�ENDFOR�");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("�ENDIF�");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("�ENDIF�");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("�ENDFOR�");
    _builder.newLine();
    return _builder;
  }
  
  public static CharSequence transferPrimitiveTypes(final String str) {
    CharSequence _xifexpression = null;
    boolean _equals = Objects.equal(str, "elong");
    if (_equals) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("type: integer");
      _builder.newLine();
      _builder.append("format: int64");
      _builder.newLine();
      _xifexpression = _builder;
    } else {
      CharSequence _xifexpression_1 = null;
      boolean _equals_1 = Objects.equal(str, "integer");
      if (_equals_1) {
        StringConcatenation _builder_1 = new StringConcatenation();
        _builder_1.append("type: integer");
        _builder_1.newLine();
        _builder_1.append("format: int32");
        _builder_1.newLine();
        _xifexpression_1 = _builder_1;
      } else {
        CharSequence _xifexpression_2 = null;
        boolean _equals_2 = Objects.equal(str, "edouble");
        if (_equals_2) {
          StringConcatenation _builder_2 = new StringConcatenation();
          _builder_2.append("type: number");
          _builder_2.newLine();
          _builder_2.append("format: double");
          _builder_2.newLine();
          _xifexpression_2 = _builder_2;
        } else {
          CharSequence _xifexpression_3 = null;
          boolean _equals_3 = Objects.equal(str, "string");
          if (_equals_3) {
            StringConcatenation _builder_3 = new StringConcatenation();
            _builder_3.append("type: string");
            _builder_3.newLine();
            _xifexpression_3 = _builder_3;
          } else {
            StringConcatenation _builder_4 = new StringConcatenation();
            _builder_4.append("�str�");
            _builder_4.newLine();
            _xifexpression_3 = _builder_4;
          }
          _xifexpression_2 = _xifexpression_3;
        }
        _xifexpression_1 = _xifexpression_2;
      }
      _xifexpression = _xifexpression_1;
    }
    return _xifexpression;
  }
}
