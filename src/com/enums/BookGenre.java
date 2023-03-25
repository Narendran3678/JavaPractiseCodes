package com.enums;

public enum BookGenre 
{
	HORROR(100)
	{
		public int kidFriendlyLimit()
		{
			return 18;
		}
	},
	COMEDY(101)
	{
		public int kidFriendlyLimit()
		{
			return 15;
		}
	};
	abstract int kidFriendlyLimit();
	public enum BookSubGenre
	{
		HORROR_THRILL,
		COMEDY_COMMERICAL;
	}
	private int genreId=0;
	
	BookGenre(int genreId) {
		this.genreId=genreId;
	}

	
	public int getGenreId() {
		return genreId;
	}
	public static void main(String args[]) throws CloneNotSupportedException
	{
		for(BookSubGenre bg:BookSubGenre.values())
		{
			System.out.print(bg.ordinal()+" ");
			System.out.print(bg.name()+" ");
			System.out.print(bg.getDeclaringClass()+" ");
			System.out.print(bg.hashCode()+" ");
			System.out.println();
		}
		for(BookGenre bg:BookGenre.values())
		{
			System.out.print(bg.ordinal()+" ");
			System.out.print(bg.getGenreId()+" ");
			System.out.print(bg.name()+" ");
			System.out.print(bg.kidFriendlyLimit()+" ");
			System.out.print(bg.getDeclaringClass()+" ");
			System.out.print(bg.hashCode()+" ");
			System.out.println();
		}	
	}
}
