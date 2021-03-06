package com.github.egor18.jdataflow.scanners;

import com.github.egor18.jdataflow.checkers.Checker;
import spoon.reflect.code.*;
import spoon.reflect.visitor.CtScanner;

/**
 * Abstract scanner, which implements Checker interface.
 */
public abstract class AbstractCheckingScanner extends CtScanner implements Checker
{
    @Override
    public abstract void checkCondition(CtExpression<?> condition);

    @Override
    public abstract void checkBinaryOperatorLeft(BinaryOperatorKind kind, CtExpression<?> left);

    @Override
    public abstract void checkBinaryOperatorRight(BinaryOperatorKind kind, CtExpression<?> right);

    @Override
    public abstract void checkBinaryOperatorResult(CtBinaryOperator<?> operator);

    @Override
    public abstract void checkConditionalThenExpression(CtExpression<?> thenExpression);

    @Override
    public abstract void checkConditionalElseExpression(CtExpression<?> elseExpression);

    @Override
    public abstract void checkConditionalResult(CtConditional<?> conditional);

    @Override
    public abstract void checkReturnedExpression(CtExpression<?> returnedExpression);

    @Override
    public abstract void checkAssignmentLeft(CtExpression<?> left);

    @Override
    public abstract void checkAssignmentRight(CtExpression<?> right);

    @Override
    public abstract void checkAssignmentResult(CtAssignment<?, ?> assignment);
}
