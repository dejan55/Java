class LinkList{
002
     
003
    // Reference to first Link in list
004
    // The last Link added to the LinkedList
005
     
006
    public Link firstLink;
007
     
008
    LinkList(){
009
         
010
        // Here to show the first Link always starts as null
011
         
012
        firstLink = null;
013
         
014
    }
015
     
016
    // Returns true if LinkList is empty
017
     
018
    public boolean isEmpty(){
019
         
020
        return(firstLink == null);
021
         
022
    }
023
     
024
    public void insertFirstLink(String bookName, int millionsSold){
025
         
026
        Link newLink = new Link(bookName, millionsSold);
027
         
028
        // Connects the firstLink field to the new Link
029
         
030
        newLink.next = firstLink;
031
         
032
        firstLink = newLink;
033
         
034
    }
035
     
036
    public Link removeFirst(){
037
         
038
        Link linkReference = firstLink;
039
         
040
        if(!isEmpty()){
041
         
042
            // Removes the Link from the List
043
         
044
            firstLink = firstLink.next;
045
         
046
        } else {
047
             
048
            System.out.println("Empty LinkedList");
049
             
050
        }
051
         
052
        return linkReference;
053
         
054
    }
055
     
056
    public void display(){
057
         
058
        Link theLink = firstLink;
059
         
060
        // Start at the reference stored in firstLink and
061
        // keep getting the references stored in next for
062
        // every Link until next returns null
063
         
064
        while(theLink != null){
065
             
066
            theLink.display();
067
             
068
            System.out.println("Next Link: " + theLink.next);
069
             
070
            theLink = theLink.next;
071
             
072
            System.out.println();
073
             
074
        }
075
         
076
    }
077
     
078
    public Link find(String bookName){
079
         
080
        Link theLink = firstLink;
081
         
082
        if(!isEmpty()){
083
         
084
            while(theLink.bookName != bookName){
085
             
086
                // Checks if at the end of the LinkedList
087
             
088
                if(theLink.next == null){
089
                 
090
                    // Got to the end of the Links in LinkedList
091
                    // without finding a match
092
                 
093
                    return null;
094
                 
095
                } else {
096
                 
097
                    // Found a matching Link in the LinkedList
098
                 
099
                    theLink = theLink.next;
100
                 
101
                }
102
             
103
            }
104
             
105
        } else {
106
             
107
            System.out.println("Empty LinkedList");
108
             
109
        }
110
         
111
        return theLink;
112
         
113
    }
114
     
115
    public Link removeLink(String bookName){
116
         
117
        Link currentLink = firstLink;
118
        Link previousLink = firstLink;
119
         
120
        // Keep searching as long as a match isn't made
121
         
122
        while(currentLink.bookName != bookName){
123
             
124
            // Check if at the last Link in the LinkedList
125
             
126
            if(currentLink.next == null){
127
                 
128
                // bookName not found so leave the method
129
                 
130
                return null;
131
                 
132
            } else {
133
                 
134
                // We checked here so let's look in the
135
                // next Link on the list
136
                 
137
                previousLink = currentLink;
138
                 
139
                currentLink = currentLink.next;
140
                 
141
            }
142
             
143
        }
144
         
145
        if(currentLink == firstLink){
146
             
147
            // If you are here that means there was a match
148
            // in the reference stored in firstLink in the
149
            // LinkedList so just assign next to firstLink
150
             
151
            firstLink = firstLink.next;
152
             
153
        } else {
154
             
155
            // If you are here there was a match in a Link other
156
            // than the firstLink. Assign the value of next for
157
            // the Link you want to delete to the Link that's
158
            // next previously pointed to the reference to remove
159
             
160
            System.out.println("FOUND A MATCH");
161
            System.out.println("currentLink: " + currentLink);
162
            System.out.println("firstLink: " + firstLink);
163
             
164
            previousLink.next = currentLink.next;
165
             
166
        }
167
         
168
        return currentLink;
169
         
170
    }
171
     
172
}
