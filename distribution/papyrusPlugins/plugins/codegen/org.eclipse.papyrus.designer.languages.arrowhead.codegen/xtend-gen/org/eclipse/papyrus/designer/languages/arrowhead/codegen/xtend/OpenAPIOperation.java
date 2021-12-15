package org.eclipse.papyrus.designer.languages.arrowhead.codegen.xtend;

import com.google.common.base.Objects;
import java.util.HashSet;
import java.util.Set;
import org.eclipse.emf.common.util.EList;
import org.eclipse.papyrus.arrowhead.profile.arrowheadsysmlprofile.DataSemantics;
import org.eclipse.papyrus.arrowhead.profile.arrowheadsysmlprofile.HttpOperation;
import org.eclipse.papyrus.designer.languages.arrowhead.codegen.utils.ParameterUtils;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.ParameterEffectKind;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.util.UMLUtil;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class OpenAPIOperation {
  private static Set<Parameter> paras = new HashSet<Parameter>();
  
  private static Set<Parameter> requests = new HashSet<Parameter>();
  
  private static Set<Parameter> responses = new HashSet<Parameter>();
  
  private static int countInstance = 0;
  
  private static boolean createSchema = true;
  
  private static boolean thisSchema = true;
  
  public static CharSequence generateOperation(final Operation op) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("�paras.clear�");
    _builder.newLine();
    _builder.append("�requests.clear�");
    _builder.newLine();
    _builder.append("�responses.clear�");
    _builder.newLine();
    _builder.append("�val hm = verifyHTTPmethod(op)��hm�:");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("operationId: �op.name�Using�hm�");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("�IF op.ownedParameters.size !== 0�");
    _builder.newLine();
    _builder.append("    \t");
    _builder.append("�FOR para : op.ownedParameters�");
    _builder.newLine();
    _builder.append("    \t\t");
    _builder.append("�verifySchema(para.type)�");
    _builder.newLine();
    _builder.append("    \t\t");
    _builder.append("�grouping(para)�");
    _builder.newLine();
    _builder.append("    \t");
    _builder.append("�ENDFOR�");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("�ENDIF�");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("�IF paras.size > 0 && requests.size > 0�");
    _builder.newLine();
    _builder.append("    \t");
    _builder.append("requestBody:");
    _builder.newLine();
    _builder.append("    \t    ");
    _builder.append("content:");
    _builder.newLine();
    _builder.append("    \t        ");
    _builder.append("application/json:");
    _builder.newLine();
    _builder.append("    \t            ");
    _builder.append("schema:");
    _builder.newLine();
    _builder.append("    \t                ");
    _builder.append("$ref: \"#/components/schemas/�generateComplexeRequestBody()�\"");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("�ELSEIF paras.size > 0 && requests.size === 0�");
    _builder.newLine();
    _builder.append("    \t");
    _builder.append("parameters:");
    _builder.newLine();
    _builder.append("    \t    ");
    _builder.append("�FOR para : paras�");
    _builder.newLine();
    _builder.append("    \t    \t");
    _builder.append("�generateParameters(para)�");
    _builder.newLine();
    _builder.append("    \t    ");
    _builder.append("�ENDFOR�");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("�ELSEIF requests.size > 0 && paras.size === 0�");
    _builder.newLine();
    _builder.append("    \t");
    _builder.append("requestBody:");
    _builder.newLine();
    _builder.append("    \t    ");
    _builder.append("�FOR para : requests�");
    _builder.newLine();
    _builder.append("    \t    \t");
    _builder.append("�generateRequestBody(para)�");
    _builder.newLine();
    _builder.append("    \t");
    _builder.append("�ENDFOR�");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("�ENDIF�");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("responses: ");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("�IF responses.size === 1�");
    _builder.newLine();
    _builder.append("        \t");
    _builder.append("�FOR para : responses�");
    _builder.newLine();
    _builder.append("        \t\t");
    _builder.append("�generateResponses(para.type, hm)�");
    _builder.newLine();
    _builder.append("        \t");
    _builder.append("�ENDFOR�");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("�ELSEIF responses.size > 1�");
    _builder.newLine();
    _builder.append("        \t");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("�ENDIF�");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("\"400\":");
    _builder.newLine();
    _builder.append("              ");
    _builder.append("description: Invalid");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("\"500\":");
    _builder.newLine();
    _builder.append("              ");
    _builder.append("description: Core service not available");
    _builder.newLine();
    _builder.newLine();
    return _builder;
  }
  
  public static CharSequence generateRequestBody(final Parameter para) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("�IF OpenAPIGenerator.schemas.contains(para.type)�");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("content:    ");
    _builder.newLine();
    _builder.append("\t    ");
    _builder.append("application/json:");
    _builder.newLine();
    _builder.append("\t        ");
    _builder.append("schema:");
    _builder.newLine();
    _builder.append("\t            ");
    _builder.append("$ref: \"#/components/schemas/�para.type.name�\"");
    _builder.newLine();
    _builder.append("�ENDIF�");
    _builder.newLine();
    return _builder;
  }
  
  public static Object generateResponseList() {
    return null;
  }
  
  public static String generateComplexeRequestBody() {
    final HashSet<Parameter> setParas = new HashSet<Parameter>();
    setParas.addAll(OpenAPIOperation.paras);
    setParas.addAll(OpenAPIOperation.requests);
    Set<org.eclipse.uml2.uml.Class> _schemas = OpenAPIGenerator.getSchemas();
    for (final org.eclipse.uml2.uml.Class schema : _schemas) {
      {
        OpenAPIOperation.thisSchema = true;
        int _size = schema.getAttributes().size();
        int _size_1 = setParas.size();
        boolean _equals = (_size == _size_1);
        if (_equals) {
          final HashSet<Type> types = new HashSet<Type>();
          for (final Parameter para : setParas) {
            types.add(para.getType());
          }
          EList<Property> _attributes = schema.getAttributes();
          for (final Property att : _attributes) {
            boolean _contains = types.contains(att.getType());
            boolean _not = (!_contains);
            if (_not) {
              OpenAPIOperation.thisSchema = false;
            }
          }
        } else {
          OpenAPIOperation.thisSchema = false;
        }
        if ((OpenAPIOperation.thisSchema == true)) {
          OpenAPIOperation.createSchema = false;
          return schema.getName();
        }
      }
    }
    if ((OpenAPIOperation.createSchema == true)) {
      final org.eclipse.uml2.uml.Class cl = UMLFactory.eINSTANCE.createClass();
      cl.setName(("ComplexRequstForm" + Integer.valueOf(OpenAPIOperation.countInstance)));
      OpenAPIOperation.countInstance++;
      for (final Parameter para : OpenAPIOperation.paras) {
        {
          cl.createOwnedAttribute(para.getName(), para.getType());
          Property _attribute = cl.getAttribute(para.getName(), para.getType());
          _attribute.setIsUnique(para.isUnique());
        }
      }
      for (final Parameter para_1 : OpenAPIOperation.requests) {
        {
          cl.createOwnedAttribute(para_1.getName(), para_1.getType());
          Property _attribute = cl.getAttribute(para_1.getName(), para_1.getType());
          _attribute.setIsUnique(para_1.isUnique());
        }
      }
      OpenAPIGenerator.getSchemas().add(cl);
      return cl.getName();
    }
    return null;
  }
  
  public static Object verifySchema(final Type cl) {
    if ((cl != null)) {
      final DataSemantics ds = UMLUtil.<DataSemantics>getStereotypeApplication(cl, DataSemantics.class);
      if ((ds != null)) {
        OpenAPIGenerator.getSchemas().add(((org.eclipse.uml2.uml.Class) cl));
      }
    }
    return null;
  }
  
  public static CharSequence generateParameters(final Parameter para) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("- name: �para.name�");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("in: query");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("required: false");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("schema: ");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("�IF para.type !== null��transferPrimitiveTypes(para.type.name.toLowerCase)��ENDIF�");
    _builder.newLine();
    return _builder;
  }
  
  public static CharSequence generateResponses(final Type type, final String hm) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\"200\": ");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("description: OK");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("�IF OpenAPIGenerator.schemas.contains(type)� ");
    _builder.newLine();
    _builder.append("    \t");
    _builder.append("content:    ");
    _builder.newLine();
    _builder.append("    \t    ");
    _builder.append("application/json:");
    _builder.newLine();
    _builder.append("    \t       ");
    _builder.append("schema:");
    _builder.newLine();
    _builder.append("    \t          ");
    _builder.append("$ref: \"#/components/schemas/�type.name�\"");
    _builder.newLine();
    _builder.append("\t  ");
    _builder.append("�ENDIF�");
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
  
  public static Object grouping(final Parameter para) {
    if ((Objects.equal(para.getDirection(), ParameterUtils.IN) && (!OpenAPIGenerator.getSchemas().contains(para.getType())))) {
      OpenAPIOperation.paras.add(para);
    }
    if ((Objects.equal(para.getDirection(), ParameterUtils.IN) && OpenAPIGenerator.getSchemas().contains(para.getType()))) {
      OpenAPIOperation.requests.add(para);
    }
    if ((Objects.equal(para.getDirection(), ParameterUtils.INOUT) && OpenAPIGenerator.getSchemas().contains(para.getType()))) {
      OpenAPIOperation.requests.add(para);
      OpenAPIOperation.responses.add(para);
    }
    ParameterDirectionKind _direction = para.getDirection();
    boolean _equals = Objects.equal(_direction, ParameterUtils.RETURN);
    if (_equals) {
      OpenAPIOperation.responses.add(para);
    }
    ParameterDirectionKind _direction_1 = para.getDirection();
    boolean _equals_1 = Objects.equal(_direction_1, ParameterUtils.OUT);
    if (_equals_1) {
      OpenAPIOperation.responses.add(para);
    }
    return null;
  }
  
  public static String verifyHTTPmethod(final Operation op) {
    final HttpOperation httpMethod = UMLUtil.<HttpOperation>getStereotypeApplication(op, HttpOperation.class);
    if ((httpMethod != null)) {
      return httpMethod.getKind().toString().toLowerCase();
    } else {
      int _size = op.getOwnedParameters().size();
      boolean _notEquals = (_size != 0);
      if (_notEquals) {
        final ParameterEffectKind effect = op.getOwnedParameters().get(0).getEffect();
        if ((effect == ParameterUtils.CREATE)) {
          return "post";
        } else {
          if ((effect == ParameterUtils.READ)) {
            return "get";
          } else {
            if ((effect == ParameterUtils.DELETE)) {
              return "delete";
            } else {
              if ((effect == ParameterUtils.UPDATE)) {
                return "put";
              } else {
                return "post";
              }
            }
          }
        }
      }
    }
    return null;
  }
}
