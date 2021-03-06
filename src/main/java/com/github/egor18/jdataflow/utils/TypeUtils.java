package com.github.egor18.jdataflow.utils;

import com.microsoft.z3.*;
import spoon.reflect.code.CtExpression;
import spoon.reflect.declaration.CtType;
import spoon.reflect.factory.Factory;
import spoon.reflect.reference.CtTypeReference;
import spoon.reflect.reference.CtWildcardReference;
import spoon.support.SpoonClassNotFoundException;

import java.util.*;

public final class TypeUtils
{
    private TypeUtils() {}

    /**
     * Returns the type of the expression with casts.
     */
    public static CtTypeReference<?> getActualType(CtExpression<?> expression)
    {
        return expression.getTypeCasts().isEmpty() ? expression.getType() : expression.getTypeCasts().get(0);
    }

    /**
     * Checks if type is null type.
     */
    public static boolean isNullType(CtTypeReference<?> type)
    {
        return type.getQualifiedName().equals("<nulltype>");
    }

    /**
     * Checks if type is 'java.lang.String'.
     */
    public static boolean isString(CtTypeReference<?> type)
    {
        return type.getQualifiedName().equals("java.lang.String");
    }

    /**
     * Checks if type is 'int' or 'java.lang.Integer'.
     */
    public static boolean isInteger(CtTypeReference<?> type)
    {
        return type.getQualifiedName().equals("int") || type.getQualifiedName().equals("java.lang.Integer");
    }

    /**
     * Checks if type is 'boolean' or 'java.lang.Boolean'.
     */
    public static boolean isBoolean(CtTypeReference<?> type)
    {
        return type.getQualifiedName().equals("boolean") || type.getQualifiedName().equals("java.lang.Boolean");
    }

    /**
     * Checks if type is 'long' or 'java.lang.Long'.
     */
    public static boolean isLong(CtTypeReference<?> type)
    {
        return type.getQualifiedName().equals("long") || type.getQualifiedName().equals("java.lang.Long");
    }

    /**
     * Checks if type is 'char' or 'java.lang.Character'.
     */
    public static boolean isChar(CtTypeReference<?> type)
    {
        return type.getQualifiedName().equals("char") || type.getQualifiedName().equals("java.lang.Character");
    }

    /**
     * Checks if type is 'byte' or 'java.lang.Byte'.
     */
    public static boolean isByte(CtTypeReference<?> type)
    {
        return type.getQualifiedName().equals("byte") || type.getQualifiedName().equals("java.lang.Byte");
    }

    /**
     * Checks if type is 'short' or 'java.lang.Short'.
     */
    public static boolean isShort(CtTypeReference<?> type)
    {
        return type.getQualifiedName().equals("short") || type.getQualifiedName().equals("java.lang.Short");
    }

    /**
     * Checks if type is 'float' or 'java.lang.Float'.
     */
    public static boolean isFloat(CtTypeReference<?> type)
    {
        return type.getQualifiedName().equals("float") || type.getQualifiedName().equals("java.lang.Float");
    }

    /**
     * Checks if type is 'double' or 'java.lang.Double'.
     */
    public static boolean isDouble(CtTypeReference<?> type)
    {
        return type.getQualifiedName().equals("double") || type.getQualifiedName().equals("java.lang.Double");
    }

    /**
     * Checks if type is 'void' or 'java.lang.Void'.
     */
    public static boolean isVoid(CtTypeReference<?> type)
    {
        return type.getQualifiedName().equals("void") || type.getQualifiedName().equals("java.lang.Void");
    }

    /**
     * Checks if the type is numeric (integral or the floating-point type).
     */
    public static boolean isNumeric(CtTypeReference<?> type)
    {
        return isChar(type)
               || isByte(type)
               || isShort(type)
               || isInteger(type)
               || isLong(type)
               || isFloat(type)
               || isDouble(type);
    }

    /**
     * Returns size of a primitive type in bits.
     */
    public static int getPrimitiveTypeSize(CtTypeReference<?> type)
    {
        switch (type.getQualifiedName())
        {
            case "byte":
            case "java.lang.Byte":
                return 8;
            case "short":
            case "java.lang.Short":
                return 16;
            case "int":
            case "java.lang.Integer":
                return 32;
            case "long":
            case "java.lang.Long":
                return 64;
            case "char":
            case "java.lang.Character":
                return 16;
            default:
                throw new RuntimeException("Unexpected type");
        }
    }

    private static Set<CtTypeReference<?>> getSuperInterfaces(CtTypeReference<?> type)
    {
        CtType<?> t = type.getTypeDeclaration();
        if (t != null)
        {
            return Collections.unmodifiableSet(t.getSuperInterfaces());
        }
        return Collections.emptySet();
    }

    /**
    * Checks if the type is java.util.List or implements it.
    */
    public static boolean isList(CtTypeReference<?> type)
    {
        if (type == null)
        {
            return false;
        }

        if (type.getQualifiedName().equals("java.util.List"))
        {
            return true;
        }
        return getSuperInterfaces(type).stream().anyMatch(TypeUtils::isList) || isList(type.getSuperclass());
    }

    /**
     * Checks if the type is calculable by the solver.
     */
    public static boolean isCalculable(CtTypeReference<?> type)
    {
        String name = type.getQualifiedName();
        return name.equals("boolean")
               || name.equals("java.lang.Boolean")
               || name.equals("byte")
               || name.equals("java.lang.Byte")
               || name.equals("short")
               || name.equals("java.lang.Short")
               || name.equals("int")
               || name.equals("java.lang.Integer")
               || name.equals("long")
               || name.equals("java.lang.Long")
               || name.equals("char")
               || name.equals("java.lang.Character");
    }

    /**
     * Checks if the type represents bit-vector value.
     */
    public static boolean isBitVector(CtTypeReference<?> type)
    {
        String name = type.getQualifiedName();
        return name.equals("byte")
            || name.equals("java.lang.Byte")
            || name.equals("short")
            || name.equals("java.lang.Short")
            || name.equals("int")
            || name.equals("java.lang.Integer")
            || name.equals("long")
            || name.equals("java.lang.Long")
            || name.equals("char")
            || name.equals("java.lang.Character");
    }

    /**
     * Checks if type is immutable (an object of that type cannot be changed after its creation).
     */
    public static boolean isImmutable(CtTypeReference<?> type)
    {
        if (type instanceof CtWildcardReference)
        {
            return false;
        }

        return type.isPrimitive()
               || type.unbox().isPrimitive()
               || type.getQualifiedName().equals("java.lang.String");
    }

    /**
     * Creates z3 sort from type.
     */
    public static Sort getTypeSort(Context context, CtTypeReference<?> type)
    {
        if (type.isPrimitive())
        {
            switch (type.getQualifiedName())
            {
                case "boolean":
                    return context.getBoolSort();
                case "byte":
                    return context.mkBitVecSort(8);
                case "short":
                    return context.mkBitVecSort(16);
                case "int":
                    return context.mkBitVecSort(32);
                case "long":
                    return context.mkBitVecSort(64);
                case "char":
                    return context.mkBitVecSort(16);
                default:
                    return context.getRealSort();
            }
        }
        else
        {
            return context.getIntSort(); // Represents address
        }
    }

    /**
     * Creates fresh z3 const from the type.
     */
    public static Expr makeFreshConstFromType(Context context, CtTypeReference<?> type)
    {
        return context.mkFreshConst("", getTypeSort(context, type));
    }

    /**
     * Creates fresh z3 bool const.
     */
    public static BoolExpr makeFreshBool(Context context)
    {
        return (BoolExpr) context.mkFreshConst("", context.getBoolSort());
    }

    /**
     * Creates fresh z3 int const.
     */
    public static IntExpr makeFreshInt(Context context)
    {
        return (IntExpr) context.mkFreshConst("", context.getIntSort());
    }

    private static Map<CtTypeReference<?>, CtTypeReference<?>> superclassesCache = new HashMap<>();

    /**
     * Returns superclass of a given type; uses caching.
     */
    public static CtTypeReference<?> getSuperclass(CtTypeReference<?> type)
    {
        if (superclassesCache.containsKey(type))
        {
            return superclassesCache.get(type);
        }
        else
        {
            CtTypeReference<?> superclass = type.getSuperclass();
            superclassesCache.put(type, superclass);
            return superclass;
        }
    }

    /**
     * Returns all superclasses of a given type; uses caching.
     */
    public static List<CtTypeReference<?>> getAllSuperclasses(CtTypeReference<?> type)
    {
        List<CtTypeReference<?>> superclasses = new ArrayList<>();
        CtTypeReference<?> s = getSuperclass(type);
        while (s != null)
        {
            superclasses.add(s);
            s = getSuperclass(s);
        }
        return superclasses;
    }

    /**
     * Returns artificial reference to array.length
     */
    public static CtTypeReference<?> getArrayLengthReference(Factory factory)
    {
        return factory.createTypeReference().setSimpleName("#ARRAY_LENGTH");
    }

    /**
     * Returns artificial reference to List.size
     */
    public static CtTypeReference<?> getListSizeReference(Factory factory)
    {
        return factory.createTypeReference().setSimpleName("#LIST_SIZE");
    }
}
