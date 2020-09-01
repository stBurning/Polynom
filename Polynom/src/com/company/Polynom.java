package com.company;

import java.util.Arrays;


public class Polynom {

    public static final double ZERO = 1e-10;
    /**
     * Коэффициенты полинома
     **/
    protected double[] a;
    /**
     * Конструктор по умолчанию:
     * - полином нулевой степени с коэффициентом 0
     **/
    public Polynom() {
        a = new double[]{0};
    }
    /**
     *
     * @param a - коэффициенты полинома
     */
    public Polynom(double[] a) {
        this.a = a;
        valid();
    }
    /**
     * Получение степени полинома
     * @return степень полинома
     */
    int getPower(){
        return a.length - 1;
    }

    private void valid(){
        int newLength = a.length;
        while (Math.abs(a[newLength - 1])<ZERO && newLength > 1)
            newLength--;

        double[] c = new double[newLength];
        System.arraycopy(a,0,c,0, newLength);
        this.a = c;
    }
    /**
     * Сложение двух полиномов
     * @param other - другой полином
     */
    public Polynom plus(Polynom other){
        Polynom minP, maxP;
        if (this.a.length > other.a.length){
            maxP = this; minP = other;
        }else {
            minP = this; maxP = other;
        }
        double[] c = new double[maxP.a.length];
        for (int i = 0; i < maxP.a.length; i++) {
            if (i < minP.a.length)
                c[i] = maxP.a[i] + minP.a[i];
        }

        return new Polynom(c);
    }
    /**
     * Умножение полинома на число
     * @param k - вещественный множитель
     */
    public Polynom times(double k){
        double[] c = a.clone();
        for (int i = 0; i < a.length; i++)
            c[i] *= k;
        return new Polynom(c);
    }
    /**
     * Деление полинома на число
     * @param k - вещественный ненулевой делитель
     */
    public Polynom div(double k) throws Exception {
        if (Math.abs(k) < ZERO)
            throw new Exception("Деление на 0.");

        return times(1/k);
    }
    /**
     * Разность полиномов
     * @param other - вычитаемый полином
     */
    public Polynom minus(Polynom other){
        return this.plus(other.times(-1));
    }

    /**
     * Произведение полиномов
     * @param other - другой полином
     */
    public Polynom mul(Polynom other){
        Polynom minP, maxP; //TODO
        if (this.a.length > other.a.length){
            maxP = this; minP = other;
        }else {
            minP = this; maxP = other;
        }
        double[] c = new double[minP.a.length];
        for (int i = 0; i < minP.a.length; i++) {
            c[i] = maxP.a[i] * minP.a[i];
        }
        return new Polynom(c);
    }
    @Override
    public String toString() {
        String out = "";
        for (int i = getPower(); i >= 0; i--) {
            if (i == 0)
                out += a[i];
            else
                out += a[i] + "x^" + i + " +" + " ";
        }
        return out;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Polynom polynom = (Polynom) o;
        return Arrays.equals(a, polynom.a);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(a);
    }
}