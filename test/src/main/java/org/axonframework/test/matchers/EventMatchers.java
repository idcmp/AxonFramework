/*
 * Copyright (c) 2010-2011. Axon Framework
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.axonframework.test.matchers;

import org.axonframework.domain.Event;
import org.hamcrest.Matcher;

/**
 * Utility class containing static methods to obtain instances of Event List Matchers.
 *
 * @author Allard Buijze
 * @since 1.1
 */
public abstract class EventMatchers {

    private EventMatchers() {
    }

    /**
     * Matches a List of Events where all the given matchers match with at least one of the Events in that list.
     *
     * @param matchers the matchers that should match against one of the items in the List of Events.
     * @return a matcher that matches a number of event-matchers against a list of events
     */
    public static ListWithAllOfMatcher listWithAllOf(Matcher<? extends Event>... matchers) {
        return new ListWithAllOfMatcher(matchers);
    }

    /**
     * Matches a List of Events where at least one of the given <code>matchers</code> matches any of the Events in that
     * list.
     *
     * @param matchers the matchers that should match against one of the items in the List of Events.
     * @return a matcher that matches a number of event-matchers against a list of events
     */
    public static ListWithAnyOfMatcher listWithAnyOf(Matcher<? extends Event>... matchers) {
        return new ListWithAnyOfMatcher(matchers);
    }

    /**
     * Matches a list of Events if each of the <code>matchers</code> match against an Event that comes after the Event
     * that the previous matcher matched against. This means that the given <code>matchers</code> must match in order,
     * but there may be "gaps" of unmatched events in between.
     * <p/>
     * To match the exact sequence of events (i.e. without gaps), use {@link #exactSequenceOf(org.hamcrest.Matcher[])}.
     *
     * @param matchers the matchers to match against the list of events
     * @return a matcher that matches a number of event-matchers against a list of events
     */
    public static SequenceOfEventsMatcher sequenceOf(Matcher<? extends Event>... matchers) {
        return new SequenceOfEventsMatcher(matchers);
    }

    /**
     * Matches a List of Events if each of the given <code>matchers</code> matches against the event at the respective
     * index in the list. This means the first matcher must match the first event, the second matcher the second event,
     * and so on.
     * <p/>
     * Any excess Events are ignored. If there are excess Matchers, they will be evaluated against <code>null</code>.
     * To make sure the number of Events matches the number of Matchers, you can append an extra {@link #andNoMore()}
     * matcher.
     * <p/>
     * To allow "gaps" of unmatched Events, use {@link #sequenceOf(org.hamcrest.Matcher[])} instead.
     *
     * @param matchers the matchers to match against the list of events
     * @return a matcher that matches a number of event-matchers against a list of events
     */
    public static ExactSequenceOfEventsMatcher exactSequenceOf(Matcher<? extends Event>... matchers) {
        return new ExactSequenceOfEventsMatcher(matchers);
    }

    /**
     * Matches against <code>null</code> or <code>void</code>. Can be used to make sure no trailing
     * events remain when using an Exact Sequence Matcher ({@link #exactSequenceOf(org.hamcrest.Matcher[])}).
     *
     * @return a matcher that matches against "nothing".
     */
    public static Matcher<? extends Event> andNoMore() {
        return nothing();
    }

    /**
     * Matches against <code>null</code> or <code>void</code>. Can be used to make sure no trailing
     * events remain when using an Exact Sequence Matcher ({@link #exactSequenceOf(org.hamcrest.Matcher[])}).
     *
     * @return a matcher that matches against "nothing".
     */
    public static Matcher<? extends Event> nothing() {
        return new NullOrVoidMatcher();
    }
}