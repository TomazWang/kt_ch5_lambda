package com.tomaz.kt_ch5_lambda.talk.t5_lambda_in_java;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/**
 * Created by TomazWang on 2019/03/23.
 *
 * @author Tomaz Wang
 * @since 2019/03/23
 **/
@SuppressWarnings("ALL")
public class UsingLambda {

    private Button btn = new Button();

    //
    // 🔑 using_kotlin_lambda
    private void usingButton() {

        //
        //
        //
        // 在 Java 中呼叫 Kotlin 的 Lambda, 需要使用 `Function` inetrface
        btn.onClick(new Function1<Button, Unit>() {
            @Override
            public Unit invoke(Button button) {
                return Unit.INSTANCE;
            }
        });

        //
        //
        //
        //
        //
        // 因為 Function1 也是 SAM, 可以改用 java 8 lambda 寫法
        btn.onClick(button -> {
            return Unit.INSTANCE; // 可惜的是在 java 中要直接使用一個 return Unit 的 lambda 必須要有回傳值
        });


        //
        //
        //
        //
        //
        //
        // 📄 回到 KtLambda.kt solve_return_unit 🔐


        //
        //
        //
        //
        //
        // 🔑 using_sam_from_kotlin
        //
        btn.onLongClick(new LongClickListener() {

            @Override
            public void onLongClick() {
                // 不需要 return 值
            }

        });

        btn.onLongClick(() -> {
            // 一樣可以換成 lambda
        });

        // 回到 KtLambda.kt 看看副作用 side_effect 🔐



        //
        //
        //
        //
        //
        //
        // 🔑 using_on_double_click
        btn.onDoubleClick(new OnDoubleClickListener() {
            @Override
            public void onDoubleClick() {
                // do something
            }
        });


        btn.onDoubleClick(() -> {
            // use java 8 lambda
        });


    }

}



// 📄 back to Main.Kt