/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tyaa.reflectionscopy;

import java.lang.reflect.Field;

/**
 *
 * @author Yurii
 */
public class ReflectionsCopy {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
        
        Person p = new Person(12, "dwafdsg");
        
        Person p1 = new Person();
        
        reflCopy(p, p1);
        
        System.out.println(p1.name);
    }
    
    //Шаблонный метод копирования значений всех полей
    //из одного объекта в другой
    private static <T> void reflCopy(T _source, T _target)
            throws IllegalArgumentException, IllegalAccessException{
        
        //Объект описания класса типа Т (заранее неизвестного)
        Class<T> metadata = (Class<T>) _source.getClass();
        //Массив описаний полей класса типа Т
        Field[] fields = metadata.getFields();
        for (Field field : fields) {
            //Получаем доступ к работе с текущим полем
            field.setAccessible(true);
            //Читаем из первого объекта значение текущего поля,
            //записываем его в такое же поле целевого объекта
            field.set(_target, field.get(_source));
        }
    }
}
