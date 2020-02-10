// Aziz Yokubjonov - aziz.yokubjonov@gmail.com
// GitHub: @aziz512
// azizwrites.xyz

import java.util.function.Consumer;

/* A node represents an artist */
class Artist implements Comparable<Artist> {
    private String name;
    private Artist next;

    public Artist(String name) {
        this.name = name;
    }

    public Artist(String name, Artist next) {
        this(name);
        this.next = next;
    }

    public String getName() {
        return this.name;
    }

    public Artist getNext() {
        return this.next;
    }

    public void setNext(Artist next) {
        this.next = next;
    }

    public int compareTo(Artist another) {
        if (another == null)
            return 1;
        return this.name.compareToIgnoreCase(another.getName());
    }
}

class TopStreamingArtists {
    private Artist first;

    public boolean isEmpty() {
        return first == null;
    }

    public void insert(String artistName) {
        Artist newArtist = new Artist(artistName);
        this.addElement(newArtist);
    }

    private void addElement(Artist newElement) {
        if (this.isEmpty()) {
            this.first = newElement;
        } else {
            this.addElement(null, this.first, newElement);
        }
    }

    private void addElement(Artist prevElem, Artist currentElem, Artist newElement) {
        if (currentElem == null) { // newElement will be the last in the list
            prevElem.setNext(newElement);
        } else if (newElement.compareTo(currentElem) <= 0) {
            // new element will come before current element
            if (prevElem == null) {
                this.first = newElement;
                this.first.setNext(currentElem);
            } else {
                newElement.setNext(currentElem);
                prevElem.setNext(newElement);
            }
        } else {
            // new element is greater --> should come after current element
            addElement(currentElem, currentElem.getNext(), newElement);
        }
    }

    public void displayList(Consumer<String> printHandler) {
        if (this.isEmpty())
            return;
        Artist current = this.first;
        while (current != null) {
            printHandler.accept(current.getName());
            current = current.getNext();
        }
    }
}