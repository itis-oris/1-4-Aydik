package ru.itis.pizza_fast.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.itis.pizza_fast.model.Pizza;
import ru.itis.pizza_fast.repository.PizzaRepository;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {
    private final PizzaRepository pizzaRepository;

    @Bean
    public CommandLineRunner pizzaDbInitializer() {
        return args -> {
            if (pizzaRepository.count() == 0) {
                pizzaRepository.save(Pizza.builder()
                        .name("Прима Пепперони большая")
                        .description("Колбаса Пепперони, сыр Моцарелла, соус Маргарита")
                        .weight(500)
                        .price(595)
                        .photo("PrimaPepperoniBig.jpg")
                        .build());
                pizzaRepository.save(Pizza.builder()
                        .name("Суперколбасная большая")
                        .description("Колбаса п/к, сервелат, колбаса Пепперони, баварские колбаски, томаты, сыр Гауда, томатный соус")
                        .weight(560)
                        .price(615)
                        .photo("SuperSausageBig.jpg")
                        .build());
                pizzaRepository.save(Pizza.builder()
                        .name("Русская")
                        .description("Колбаса п/к, колбаса варёная, марин.огурцы, болг.перец, сыр Гауда и Моцарелла, горчичный соус")
                        .weight(540)
                        .price(495)
                        .photo("Russian.jpg")
                        .build());
                pizzaRepository.save(Pizza.builder()
                        .name("Азиатская")
                        .description("Ветчина, цыпленок копченый, свежие томаты, перец болгарский, сыр Моцарелла, соусы терияки и орехово-кунжутный")
                        .weight(510)
                        .price(595)
                        .photo("Asian.jpg")
                        .build());
                pizzaRepository.save(Pizza.builder()
                        .name("С карпаччо и томатами")
                        .description("Карпаччо из свинины, ветчина, свежие томаты, сыр Моцарелла, соус Цезарь")
                        .weight(545)
                        .price(555)
                        .photo("Carpaccio.jpg")
                        .build());
                pizzaRepository.save(Pizza.builder()
                        .name("Маргарита")
                        .description("Свежие томаты, маслины и сыр Моцарелла на томатном соусе с итальянскими травами")
                        .weight(500)
                        .price(495)
                        .photo("Margarita.jpg")
                        .build());
                pizzaRepository.save(Pizza.builder()
                        .name("Мясная BBQ")
                        .description("Колбаса Пепперони, карпаччо из свинины, ветчина, цыпленок копченый, сыр Моцарелла, соусы томатный и барбекю")
                        .weight(540)
                        .price(635)
                        .photo("MeatBBQ.jpg")
                        .build());
                pizzaRepository.save(Pizza.builder()
                        .name("Пан Картофан")
                        .description("Грудинка, бекон, шампиньоны, марин.огурцы, картофель Айдахо, сыры Моцарелла и Гоюс, чесночный соус")
                        .weight(710)
                        .price(565)
                        .photo("MrPotato.jpg")
                        .build());
                pizzaRepository.save(Pizza.builder()
                        .name("Дракон")
                        .description("Ветчина, колбаса Пепперони, томаты, марин.огурцы, острый перец халапеньо, сыр Моцарелла, соус из печеных перцев Чипотл")
                        .weight(615)
                        .price(565)
                        .photo("Dragon.jpg")
                        .build());
            }
        };
    }
} 