package org.eclipse.papyrus.designer.languages.arrowhead.codegen.xtend;

import org.eclipse.uml2.uml.Type;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class OpenAPIPaths {
  public static CharSequence generatePath(final Type classifier) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("�IF classifier instanceof Classifier�");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("�FOR op : classifier.allOperations�");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("/�classifier.name�/�op.name�:");
    _builder.newLine();
    _builder.append("\t\t    ");
    _builder.append("�OpenAPIOperation.generateOperation(op)�");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("�ENDFOR�");
    _builder.newLine();
    _builder.append("�ENDIF�");
    _builder.newLine();
    return _builder;
  }
}
