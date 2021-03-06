package com.github.egor18.jdataflow.checkers;

import com.github.egor18.jdataflow.memory.Memory;
import com.github.egor18.jdataflow.misc.ConditionStatus;
import com.github.egor18.jdataflow.scanners.CheckersScanner;
import com.github.egor18.jdataflow.scanners.DataFlowScanner;
import com.github.egor18.jdataflow.warning.Warning;
import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;
import com.microsoft.z3.Solver;
import spoon.reflect.code.*;

/**
 * Abstract checker class, which is a superclass of all checkers.
 * It implements Checker interface and provides some proxy methods to interact with scanner.
 */
public abstract class AbstractChecker implements Checker
{
    private CheckersScanner scanner;

    protected AbstractChecker(CheckersScanner scanner)
    {
        this.scanner = scanner;
    }

    protected DataFlowScanner getScanner()
    {
        return scanner;
    }

    protected Context getContext()
    {
        return scanner.getContext();
    }

    protected Solver getSolver()
    {
        return scanner.getSolver();
    }

    protected Memory getMemory()
    {
        return scanner.getMemory();
    }

    protected boolean isInsideLoopEntryCondition()
    {
        return scanner.isInsideLoopEntryCondition();
    }

    protected ConditionStatus checkCond(BoolExpr conditionExpr)
    {
        return scanner.checkCond(conditionExpr);
    }

    protected void addWarning(Warning warning)
    {
        scanner.addWarning(warning);
    }

    @Override
    public void checkCondition(CtExpression<?> condition) {}

    @Override
    public void checkBinaryOperatorLeft(BinaryOperatorKind kind, CtExpression<?> left) {}

    @Override
    public void checkBinaryOperatorRight(BinaryOperatorKind kind, CtExpression<?> right) {}

    @Override
    public void checkBinaryOperatorResult(CtBinaryOperator<?> operator) {}

    @Override
    public void checkConditionalThenExpression(CtExpression<?> thenExpression) {}

    @Override
    public void checkConditionalElseExpression(CtExpression<?> elseExpression) {}

    @Override
    public void checkConditionalResult(CtConditional<?> conditional) {}

    @Override
    public void checkReturnedExpression(CtExpression<?> returnedExpression) {}

    @Override
    public void checkAssignmentLeft(CtExpression<?> left) {}

    @Override
    public void checkAssignmentRight(CtExpression<?> right) {}

    @Override
    public void checkAssignmentResult(CtAssignment<?, ?> assignment) {}

    @Override
    public void checkInvocation(CtInvocation<?> invocation) {}

    @Override
    public void checkFieldRead(CtFieldRead<?> fieldRead) {}

    @Override
    public void checkFieldWrite(CtFieldWrite<?> fieldWrite) {}

    @Override
    public void checkArrayRead(CtArrayRead<?> arrayRead) {}

    @Override
    public void checkArrayWrite(CtArrayWrite<?> arrayWrite) {}
}
