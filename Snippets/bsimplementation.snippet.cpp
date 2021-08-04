// Based on
// https://leetcode.com/discuss/general-discussion/786126/python-powerful-ultimate-binary-search-template-solved-many-problems
bool condition(int param){
    // TODO: Change this and remove this comment
    return false; // Placeholder
}

int bs(){
    // TODO: Ensure the below two lines are right and remove this comment
    int left = 0; // minimum of search
    int right = 1; // maximum of search
    while(left < right){
        int mid = left + (right - left)/2;
        if(condition(mid)){
            right = mid;
        }else{
            left = mid + 1;
        }
    }
    return left; // TODO: remove this message after checking if return left; should be used (min satsifying condition)
}