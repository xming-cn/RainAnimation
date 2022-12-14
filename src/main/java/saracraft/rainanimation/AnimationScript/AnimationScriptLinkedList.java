package saracraft.rainanimation.AnimationScript;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AnimationScriptLinkedList {

    private static class Note {
        Note next;
        AnimationSegment value;

        public Note(AnimationSegment value) {
            this.value = value;
        }

        public Note getNext() {
            return next;
        }

        public void setNext(Note next) {
            this.next = next;
        }

        public AnimationSegment getValue() {
            return value;
        }

        public void setValue(AnimationSegment value) {
            this.value = value;
        }
    }
    Note first;

    public static AnimationScriptLinkedList parseLinkedList(List<String> segments) {
        ArrayList<AnimationSegment> segmentList = new ArrayList<>();
        for (String segment : segments) {
            AnimationSegment thisSegment = AnimationSegment.parseSegment(segment);
            segmentList.add(thisSegment);
        }
        return new AnimationScriptLinkedList(segmentList);
    }

    public AnimationScriptLinkedList(ArrayList<AnimationSegment> segments) {
        Note previous = null;
        for (AnimationSegment segment : segments) {
            Note newNote = new Note(segment);
            if (this.first == null) first = newNote;
            if (previous != null) previous.next = newNote;
            previous = newNote;
        }
    }

    public Note findLastNote() {
        Note last = first;
        while(last.next != null) last = last.next;
        return last;
    }

    public void addEnd(AnimationSegment a) {
        findLastNote().setNext(new Note(a));
    }

    public AnimationSegment step() {
        if (Objects.isNull(first)) return null;
        AnimationSegment result = first.value;
        first = first.next;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        Note current = first;
        while (current != null) {
            result.append(" -> ");
            result.append(current.value.toString());
            current = current.next;
        }
        return result.toString();
    }

    public Note getFirst() {
        return first;
    }

    public void setFirst(Note first) {
        this.first = first;
    }
    public void end() {
        this.first = null;
    }
}
