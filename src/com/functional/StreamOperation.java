package com.functional;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class StreamOperation {

	static void collectGroup(List<Book> bookList)
	{
		Map<Double,Long> map4= bookList.stream().collect(Collectors.groupingBy(Book::getRating,Collectors.counting()));
		System.out.println(map4);
		
		Map<Double,Double> map5= bookList.stream().collect(Collectors.groupingBy(Book::getRating, Collectors.averagingDouble((b) -> b.getPrice())));
		System.out.println(map5);
		
		Map<Double,Optional<Book>> map6= bookList.stream().collect(Collectors.groupingBy(Book::getRating,Collectors.minBy(Comparator.comparingDouble(Book::getPrice))));
		System.out.println(map6);
		
		Map<Double,DoubleSummaryStatistics> map7= bookList.stream().collect(Collectors.groupingBy(Book::getRating,Collectors.summarizingDouble((d) -> d.getPrice())));
		System.out.println(map7);
		
		Map<Boolean,List<Book>> map8 = bookList.stream().collect(Collectors.partitioningBy( b -> b.getRating() >=4.5 ));
		System.out.println(map8);

		Map<Double,List<String>> map9 = bookList.stream().collect(Collectors.groupingBy(Book::getRating,Collectors.mapping(Book::getTitle,Collectors.toList())));
		System.out.println(map9);
		
	}
	static void collect(List<Book> bookList) {
		System.out.println("Collect Reduction");
		System.out.println(Arrays.stream(new String[] { "A", "B", "C" }).parallel().collect(() -> new StringBuilder(),
				(sb, s) -> sb.append(s), (sb1, sb2) -> sb1.append(sb2)));
		System.out.println(Arrays.stream(new String[] { "A", "B", "C" }).parallel().collect(Collectors.joining()));

		System.out.println("Collect Reduction");
		List<String> list = Arrays.stream(new String[] { "B", "A", "C", "A" }).collect(Collectors.toList());
		list.forEach(System.out::print);
		System.out.println();

		Set<String> set1 = Arrays.stream(new String[] { "B", "A", "C", "A" }).collect(Collectors.toSet());
		set1.forEach(System.out::print);
		System.out.println();

		TreeSet<String> set2 = Arrays.stream(new String[] { "B", "A", "C", "A" })
				.collect(Collectors.toCollection(() -> new TreeSet<>()));
		set2.forEach(System.out::print);
		System.out.println();

		Map<Long, Book> map1 = bookList.stream().collect(
				Collectors.toMap((b) -> b.getIsbn(), b -> b, (b1, b2) -> b1.getPrice() <= b2.getPrice() ? b1 : b2));
		// map1.forEach((l,b) ->{ System.out.println(l+"->"+b);});
		for (Entry<Long, Book> en : map1.entrySet())
			System.out.println(en.getKey() + "->" + en.getValue());

		System.out.println("Low Level Grouping Logic");
		Map<Double, List<Book>> map2 = bookList.stream()
				.collect(Collectors.toMap((b) -> b.getRating(), b -> Collections.singletonList(b), (l1, l2) -> {
					List<Book> li = new ArrayList<>();
					li.addAll(l1);
					li.addAll(l2);
					return li;
				}));
		for (Entry<Double, List<Book>> en : map2.entrySet()) {
			System.out.println(en.getKey());
			en.getValue().forEach(System.out::println);
		}
		
		System.out.println("High Level Grouping Logic");
		Map<Double, List<Book>> map3 = bookList.stream().collect(Collectors.groupingBy(b -> b.getRating()));
		for (Entry<Double, List<Book>> en : map3.entrySet()) {
			System.out.println(en.getKey());
			en.getValue().forEach(System.out::println);
		}
		
		
	}

	static void reduce(List<Book> bookList) {
		List<Integer> intList = Arrays.asList(11, 2, 31, -4, 15);
		Integer lowValue = intList.stream().reduce(9999, (i1, i2) -> {
			return i1 < i2 ? i1 : i2;
		});
		System.out.println(lowValue);

		System.out.println(Arrays.stream(new String[] { "A", "B", "C" }).reduce("", (s1, s2) -> s1 + s2));

		StringBuilder strB = Arrays.stream(new String[] { "A", "B", "C" }).reduce(new StringBuilder(),
				(sb, s) -> sb.append(s), (sb1, sb2) -> sb1.append(sb2));

		System.out.println(strB.toString());

		// mutable Reduction
		System.out.println("Mutable Reduction");
		System.out.println(
				Arrays.stream(new String[] { "A", "B", "C" }).parallel().reduce("", (s1, s2) -> s1.concat(s2)));

		System.out.println(Arrays.stream(new String[] { "A", "B", "C" }).parallel().reduce(new StringBuilder(),
				(sb, s) -> sb.append(s), (sb1, sb2) -> sb1.append(sb2)));

	}

	static void find(List<Book> bookList) {
		Optional<Book> opt = bookList.stream().filter(d -> d.getRating() >= 4).findAny();
		// .ifPresent( d -> System.out.println(d));
		if (opt.isPresent()) {
			System.out.println(opt.get());
		} else {
			System.out.println("Not Found");
		}
		bookList.stream().filter(d -> d.getRating() >= 4).findAny().ifPresentOrElse(System.out::println,
				() -> System.out.println("Not Found"));
	}

	static void match(List<Book> bookList) {
		System.out.println(bookList.stream().allMatch(d -> d.getRating() >= 4));
		System.out.println(bookList.stream().anyMatch(d -> d.getRating() >= 4));
		System.out.println(bookList.stream().noneMatch(d -> d.getRating() >= 4));
	}

	static void slice(List<Book> bookList) {
		List<String> finalList = bookList.stream().filter(b -> b.getRating() >= 4.5).distinct()
				.peek(b -> System.out.println("Peeked Value.." + b.getTitle()))
				// .limit(5)
				// .skip(5)
				.map(b -> b.getTitle()).collect(Collectors.toList());
		// .forEach(System.out::println);

		finalList.forEach(System.out::println);
	}

	public static void main(String args[]) {
		List<Book> listBook = new ArrayList<Book>();
		listBook.addAll(DataPublisher.getFromAmazon("Java"));
		listBook.addAll(DataPublisher.getFromBarnesAndNoble("Java"));
		// slice(listBook);

		// match(listBook);

		// find(listBook);

		// reduce(listBook);

		// collect(listBook);
		
		collectGroup(listBook);
	}
}
