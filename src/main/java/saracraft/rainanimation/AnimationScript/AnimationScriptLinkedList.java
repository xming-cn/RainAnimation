package saracraft.rainanimation.AnimationScript;

import java.util.ArrayList;

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
    Note current;

    public AnimationScriptLinkedList(ArrayList<AnimationSegment> segments) {
        Note previous = null;
        for (AnimationSegment segment : segments) {
            Note newNote = new Note(segment);
            if (previous != null) previous.next = newNote;
            previous = newNote;
        }
    }

    public AnimationSegment step() {
        AnimationSegment result = current.value;
        current = current.next;
        return result;
    }
}
