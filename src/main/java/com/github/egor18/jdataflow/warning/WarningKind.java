package com.github.egor18.jdataflow.warning;

public enum WarningKind
{
    EXCEPTION("Exception occurred during the analysis: %s"),
    TIMEOUT("Timed out analyzing element"),
    ALWAYS_TRUE("Condition '%s' is always true when reached"),
    ALWAYS_FALSE("Condition '%s' is always false when reached"),
    NULL_DEREFERENCE("Null dereference occurs in the '%s' expression"),
    ;

    String message;

    WarningKind(String message)
    {
        this.message = message;
    }
}
